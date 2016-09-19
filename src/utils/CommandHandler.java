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
		if(args[0].equals("create"))
		{
			commands.createCommand(key,args);
			comm = commands.findCommand(key+args[0]);
			if(comm != null)
			{
				comm.sendMessage(event.getTextChannel(), args);
				return true;
			}
		}
		else
		{
			comm = commands.findCommand(key+args[0]);
			if(comm != null)
			{
				comm.sendMessage(event.getTextChannel(), args);
				return true;
			}
		}
		return false;
	}
}
