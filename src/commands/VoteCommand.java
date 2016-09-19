package commands;

import java.io.File;
import java.util.ArrayList;

import net.dv8tion.jda.OnlineStatus;
import net.dv8tion.jda.entities.TextChannel;
import net.dv8tion.jda.entities.User;

public class VoteCommand extends ParameterCommand
{
	private boolean finished;
	private int noVotes;
	private int yesVotes;
	private boolean timedOut;
	private long t0;
	private ArrayList<User> usersVoted;
	private Thread timer;
	private static TextChannel channel;
	public VoteCommand(String m, File f, String n, int maxU) 
	{
		super(m, f, n, maxU);
		yesVotes = 0;
		noVotes = 0;
		t0 = System.nanoTime();
		usersVoted = new ArrayList<User>();
		finished = false;
		initTimer();
	}
	
	@Override
	public void sendMessage(TextChannel chann, String[] args)
	{
		channel = chann;
		if((System.nanoTime()-t0)/1000000>=1)
		{
			t0 = System.nanoTime();
			uses=0;
		}
		System.out.println("ENTERING SEND MESSAGE OF "+name+" WITH USES "+uses);
		if(uses<maxUses)
		{
			giveInput(channel,args);
			System.out.println("PASSED USAGE CHECK "+(file==null));
			if(message != null)
			{
				System.out.println("SENDING "+name+" COMMAND WITH MESSAGE "+message);
				channel.sendMessage(message);
			}
			if(file != null)
			{
				System.out.println("OUTPUTTING FILE "+file.toPath()+" TO CHANNEL"+channel);
				channel.sendFile(file, null);
			}
		}
		message = null;
	}
	
	@Override
	public void giveInput(TextChannel chann, String[] args){}
	public void giveInput(TextChannel chann, String[] args, User u) 
	{	
		channel = chann;
		if(args == null){return;}
		int index = 0;
		for(int i=0;i<channel.getGuild().getUsers().size();i++)	
		{
			if(channel.getGuild().getUsers().get(i).getOnlineStatus() == OnlineStatus.ONLINE)
			{
				index++;
			}
		}
		index-=1;
		System.out.println("VOTE COMMAND INPUT WITH ARGS ");
		for(int i=0;i<args.length;i++)
		{
			System.out.println(args[i]);
		}
		System.out.println("BREAK PARAMS: "+usersVoted.size()+" "+index+" "+timedOut);
		if(usersVoted.size()<index && !timedOut)
		{
			switch(args[0])
			{
				case "yes":
					voteYes(u);
					break;
				case "yay":
					voteYes(u);
					break;
				case "I":
					voteYes(u);
					break;
				case "yeah":
					voteYes(u);
					break;
				case "do":
					voteYes(u);
					break;
					
				case "nay":
					voteNo(u);
					break;
				case "no":
					voteNo(u);
					break;
				case "don't":
					voteNo(u);
					break;
			}
		}
		if(usersVoted.size()>=index)
		{
			sendResults();
		}
	}
	public void sendResults()
	{
		message = "The vote has ended! The results of the vote are: \n"+yesVotes+" in favor of "+name+"\n"+noVotes+" against "+name;
		sendMessage(channel,null);
		finished = true; 
	}
	public void voteYes(User u)
	{
		if(!usersVoted.contains(u))
		{
			yesVotes++;
			usersVoted.add(u);
		}
	}
	public void voteNo(User u)
	{
		if(!usersVoted.contains(u))
		{
			noVotes++;
			usersVoted.add(u);
		}
	}
	public void initTimer()
	{
		if(timer == null)
		{
			timer = new Thread()
			{
				public void run()
				{
					while(!timedOut)
					{
						timedOut = (System.nanoTime()-t0)/1000000000>=30;
						try{timer.sleep(1000);}
						catch(Exception e){}
					}
					sendResults();
				}
			};
			timer.start();
		}
	}
	public boolean hasFinished(){return finished;}
}
