package main;

import net.dv8tion.jda.*;
import utils.*;

public class Main
{
	private JDABuilder bot;
	public static CommandParser parser;
	private static ChatListener listener;
	private static CommandList commands;
	private static CommandHandler handler;
	public Main()
	{
		commands = new CommandList();
		parser = new CommandParser();
		handler = new CommandHandler(commands);
		listener  = new ChatListener(parser,handler);
		bot = new JDABuilder();
		try 
		{
			bot.addListener(listener);
			bot.setBotToken(/*TOKEN*/).buildBlocking();
			bot.setAutoReconnect(true);
			System.out.println("finished building");
		}
		catch (Exception e){e.printStackTrace();}
		System.out.println("DID SOMEBODY RING THE DINKSTER?");
	}
	
	

	public static void main(String[] args) 
	{
		new Main();
	}

}
