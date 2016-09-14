package commands;

import java.io.File;
import java.util.ArrayList;

import net.dv8tion.jda.entities.TextChannel;

public class RandomCommand extends Command
{
	private ArrayList<String> messages;
	private ArrayList<File> files;
	private boolean tickAmbiently;
	private Thread t;
	private TextChannel channel;
	public RandomCommand(String m, File f, String n, int maxU, ArrayList<String> ms, ArrayList<File> fs, boolean ambience, TextChannel chan, int sleepSeconds) 
	{
		super(m, f, n, maxU);
		messages = ms;
		channel = chan;
		files = fs;
		tickAmbiently = ambience;
		if(ambience)
		{
			t = new Thread()
			{
				public void run()
				{
					while(true)
					{
						
						if(messages != null)
						{
							message = messages.get((int)(Math.random()*messages.size()));
						}
						if(files != null)
						{
							file = files.get((int)(Math.random()*files.size()));
						}
						sendMessage(channel);
						try{t.sleep(sleepSeconds*1000);}
						catch(Exception e){}
					}
				}
			};
			t.start();
		}
		else
		{
			t = null;
		}
	}
	
	@Override
	public void sendMessage(TextChannel channel)
	{
		if(!tickAmbiently && t == null)
		{
			if(messages != null)
			{
				message = messages.get((int)(Math.random()*messages.size()));
			}
			if(files != null)
			{
				file = files.get((int)(Math.random()*files.size()));
			}
		}
		if((System.nanoTime()-t0)/1000000>=1)
		{
			t0 = System.nanoTime();
			uses=0;
		}
		if(uses>maxUses)
		{
			if(message != null)
			{
				channel.sendMessage(message);
			}
			if(file != null)
			{
				channel.sendFile(file, null);
			}
		}
	}
	public void setAmbience(boolean i){tickAmbiently = i;}
	public boolean isAmbient(){return tickAmbiently;}
}
