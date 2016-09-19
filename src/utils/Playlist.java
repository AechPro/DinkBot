package utils;

import java.io.File;
import java.util.ArrayList;

import commands.Command;
import commands.DefaultCommand;

import net.dv8tion.jda.entities.TextChannel;

public class Playlist 
{
	private ArrayList<Command> commands;
	private Thread t;
	private int sleepTime;
	private static TextChannel channel;
	public int index;
	private String name;
	public Playlist(ArrayList<Command> comms, String n, int playSpeedSeconds)
	{
		name = n;
		System.out.println("playlist constructor 1");
		channel = null;
		commands = comms;
		sleepTime = playSpeedSeconds*1000;
		t = null;
		init();
	}
	public Playlist(String n, String filePath, int playSpeedSeconds)
	{
		name = n;
		System.out.println("playlist constructor 2");
		channel = null;
		commands = loadCommands(filePath);
		sleepTime = playSpeedSeconds*1000;
		t = null;
		init();
	}
	public void init()
	{
		System.out.println("playlist initializer");
		if(t == null)
		{
			System.out.println("instantiating thread");
			t = new Thread()
			{
				@SuppressWarnings("static-access")
				public void run()
				{
					while(true)
					{
						//System.out.println("channel: "+(channel == null));
						if(channel != null)
						{
							System.out.println("playlist channel not null, outputting next item");
							if(index >= commands.size()){index=0;}
							commands.get(index).sendMessage(channel,null);
							index++;
							try{t.sleep(sleepTime);}
							catch(Exception e){e.printStackTrace();}
						}
						try{t.sleep(160);}
						catch(Exception e){}
					}
				}
			};
			t.start();
		}
	}
	public void shuffle()
	{
		Command temp = null;
		int ran = 0;
		for(int i=0;i<commands.size();i++)
		{
			ran = (int)(Math.random()*commands.size());
			temp = commands.get(ran);
			commands.set(ran, commands.get(i));
			commands.set(i, temp);
		}
	}
	public void play(TextChannel chann){channel = chann;}
	public void pause(){channel = null;}
	public void random(TextChannel chann)
	{
		commands.get((int)(Math.random()*commands.size())).sendMessage(chann,null);
	}
	public void restart(){index=0;}
	public void giveInput(String input, TextChannel chann) //MUST BE PARSED TO VALID COMMANDS ONLY
	{
	}
	public ArrayList<Command> loadCommands(String fp)
	{
		System.out.println("LOADING FILES FROM "+fp);
		ArrayList<Command> comms = new ArrayList<Command>();
		File folder = new File("resources/"+fp);
		File[] files = folder.listFiles();
		for(int i=0;i<files.length;i++)
		{
			System.out.println("loading file "+files[i].toPath()+" to playlist");
			comms.add(new DefaultCommand(null,files[i],"playlist file "+i,1));
		}
		return comms;
	}
	public String getName(){return name;}
	public void setChannel(TextChannel i){channel=i;}
	public TextChannel getChannel(){return channel;}
}
