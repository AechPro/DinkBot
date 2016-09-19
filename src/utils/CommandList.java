package utils;

import java.io.File;
import java.util.HashMap;

import commands.*;

public class CommandList 
{
	private static HashMap<String, Command> commandMap;
	public CommandList()
	{
		fillCommandList();
	}
	
	public Command findCommand(String key)
	{
		if(!commandMap.containsKey(key)){return null;}
		return commandMap.get(key);
	}
	public void createCommand(String key, String[] args)
	{
		switch(key)
		{
			case "playlist":
				commandMap.put(key+args[1], new PlaylistCommand(null,null,args[1],1));
				break;
			case "vote":
				commandMap.put(key+args[1], new VoteCommand(null,null,args[1],1));
				break;
			case "roll":
				commandMap.put(key+args[1], new RollCommand(null,null,args[1],1));
				break;
		}
		if(commandMap.get(key+args[1]).create(args))
		{
			System.out.println("COMMAND ADDED SUCCESSFULLY");
		}
		else{System.out.println("COMMAND COULD NOT BE ADDED");}
	}
	private void fillCommandList()
	{
		commandMap = new HashMap<String, Command>();
		String helpString = "The currently available commands are: \n !help \n !burn \n !kappa \n !ping \n !gonbgud \n !triggered \n !vote [args] \n !playlist [args] \n !roll [args]";
		
		int maxMessagesPerSecond = 10;
		DefaultCommand kappa = new DefaultCommand(null, new File("resources/twitch emotes/Kappa.png"),"Kappa",maxMessagesPerSecond);
		DefaultCommand triggered = new DefaultCommand(null, new File("resources/triggered.png"),"Triggered",maxMessagesPerSecond);
		DefaultCommand help = new DefaultCommand(helpString, null, "Help",maxMessagesPerSecond);
		DefaultCommand ping = new DefaultCommand("Pong!", null, "Ping",maxMessagesPerSecond);
		DefaultCommand burn = new DefaultCommand("Let me help you with that:\n https://en.wikipedia.org/wiki/List_of_burn_centers_in_the_United_States", null, "Burn",maxMessagesPerSecond);
		DefaultCommand gonbgud = new DefaultCommand(null,new File("resources/gonbgud.gif"),"GonBGud",maxMessagesPerSecond);
		DefaultCommand karl = new DefaultCommand("Text sent.",null,"karl",maxMessagesPerSecond*10);
		DefaultCommand number = new DefaultCommand("35",null,"number",maxMessagesPerSecond*10);
		
		commandMap.put(number.getName().toLowerCase(),number);
		commandMap.put(karl.getName().toLowerCase(),karl);
		commandMap.put(kappa.getName().toLowerCase(),kappa);
		commandMap.put(triggered.getName().toLowerCase(),triggered);
		commandMap.put(help.getName().toLowerCase(),help);
		commandMap.put(ping.getName().toLowerCase(),ping);
		commandMap.put(burn.getName().toLowerCase(),burn);
		commandMap.put(gonbgud.getName().toLowerCase(),gonbgud);
	}
	
}
