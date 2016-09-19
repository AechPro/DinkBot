package utils;

import commands.*;

import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class CommandHandler 
{
	private static CommandList commands;
	public CommandHandler(CommandList comms)
	{
		commands = comms;
	}
	public boolean handleCommand(CommandParser.CommandContainer container)
	{
		String key = container.INVOKE;
		MessageReceivedEvent event = container.EVENT;
		Command comm = commands.findCommand(key);
		String[] args = container.ARGS;
		
		System.out.println("handling message with key "+key);
		if(comm != null)
		{
			System.out.println("command with key "+key+" found");
			System.out.println("executing command: "+key+" with args: "+args.toString());
			comm.sendMessage(event.getTextChannel());
			return true;
		}
		else if(key.equals("playlist"))
		{
			System.out.println("PLAYLIST COMMAND INTERPRETED WITH NAME "+args[0]);
			if(commands.findPlaylistCommand(args[0]) != null)
			{
				System.out.println("FOUND PLAYLIST "+args[0]);
				commands.findPlaylistCommand(args[0]).sendMessage(event.getTextChannel(),args);
			}
			else if(args[0].equals("create"))
			{
				System.out.println("CREATING PLAYLIST FROM ARGS");
				for(int i=0;i<args.length;i++)
				{
					System.out.println(args[i]);
				}
				commands.addPlaylist(args);
			}
			return true;
		}
		else if(key.equals("vote"))
		{
			if(commands.getCurrentVote() == null)
			{
				String voteName = "";
				for(int i=0;i<args.length;i++)
				{
					voteName+=args[i]+" ";
				}
				String defaultVoteMessage = "Vote for "+voteName+"\ncreated!";
				commands.setVote(new VoteCommand(defaultVoteMessage, null, voteName, 1));
				commands.getCurrentVote().sendMessage(event.getTextChannel(), null);
				commands.getCurrentVote().setMessage(null);
			}
			else
			{
				if(commands.getCurrentVote().hasFinished())
				{
					commands.setVote(null);
					String voteName = "";
					for(int i=0;i<args.length;i++)
					{
						voteName+=args[i]+" ";
					}
					String defaultVoteMessage = "Vote for "+voteName+"\ncreated!";
					commands.setVote(new VoteCommand(defaultVoteMessage, null, voteName, 1));
					commands.getCurrentVote().sendMessage(event.getTextChannel(), null);
					commands.getCurrentVote().setMessage(null);
				}
				else
				{ 
					commands.getCurrentVote().giveInput(event.getTextChannel(),args,event.getAuthor());
				}
			}
		}
		else if(key.equals("roll"))
		{
			if(commands.getCurrentRoll() == null)
			{
				commands.setCurrentRoller(new RollCommand(null, null, "roller", 1));
				commands.getCurrentRoll().sendMessage(event.getTextChannel(), args);
			}
			else
			{
				commands.getCurrentRoll().sendMessage(event.getTextChannel(), args);
			}
		}
		return false;
	}
}
