package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import commands.*;
import net.dv8tion.jda.events.message.MessageReceivedEvent;

public class CommandList 
{
	private static HashMap<String, Command> commands;
	private static ArrayList<String> specialChatKeys;
	public CommandList()
	{
		specialChatKeys = new ArrayList<String>();
		fillCommandList();
	}
	public boolean handleCommand(CommandParser.CommandContainer cmd)
	{
		System.out.println("handle command with "+cmd.INVOKE);
		if(commands.containsKey(cmd.INVOKE))
		{
			System.out.println("key "+cmd.INVOKE+" found");
			commands.get(cmd.INVOKE).sendMessage(cmd.EVENT.getTextChannel());
		}
		return commands.containsKey(cmd.INVOKE);
	}
	public boolean handleCommand(String key, MessageReceivedEvent event)
	{
		System.out.println("received string key: "+key);
		String mapKey = "";
		for(int i=0;i<specialChatKeys.size();i++)
		{
			if(key.contains(specialChatKeys.get(i)))
			{
				mapKey = specialChatKeys.get(i);
				break;
			}
		}
		if(mapKey.equals("")){return false;}
		System.out.println("found command with key "+mapKey);
		commands.get(mapKey).sendMessage(event.getTextChannel());
		return true;
	}
	/*
	//NOT MY CODE
	private ArrayList<String> getMapspecialChatKeys() 
	{
		ArrayList<String> specialChatKeys = new ArrayList<String>();
	    Iterator<Entry<String, Command>> it = commands.entrySet().iterator();
	    while (it.hasNext()) 
	    {
	        HashMap.Entry pair = (HashMap.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        specialChatKeys.add(pair.getKey().toString());
	        it.remove(); // avoids a ConcurrentModificationException
	    }
	    return specialChatKeys;
	}
	//THANKS INTERNET
	*/
	private void fillCommandList()
	{
		commands = new HashMap<String, Command>();
		
		String helpString = "The currently available commands are: \n !help \n !burn \n !kappa \n !ping \n !gonbgud \n !triggered";
		int maxMessagesPerSecond = 1;
		DefaultCommand kappa = new DefaultCommand(null, new File("resources/twitch emotes/Kappa.png"),"Kappa",maxMessagesPerSecond);
		DefaultCommand triggered = new DefaultCommand(null, new File("resources/triggered.png"),"Triggered",maxMessagesPerSecond);
		DefaultCommand help = new DefaultCommand(helpString, null, "Help",maxMessagesPerSecond);
		DefaultCommand ping = new DefaultCommand("Pong!", null, "Ping",maxMessagesPerSecond);
		DefaultCommand burn = new DefaultCommand("Let me help you with that:\n https://en.wikipedia.org/wiki/List_of_burn_centers_in_the_United_States", null, "Burn",maxMessagesPerSecond);
		DefaultCommand gonbgud = new DefaultCommand(null,new File("resoruces/gonbgud.gif"),"GonBGud",maxMessagesPerSecond);
		DefaultCommand whoIsMatt = new DefaultCommand("This man sexually identifies as a neckbeard:",new File("resources/matt unveiled.png"),"Who is matt",maxMessagesPerSecond*10);
		DefaultCommand dinkster = new DefaultCommand("DID SOMEBODY RING THE DINKSTER?!",null,"dinkster",maxMessagesPerSecond*10);
		
		commands.put(whoIsMatt.getName().toLowerCase(),whoIsMatt);
		commands.put(dinkster.getName().toLowerCase(),dinkster);
		commands.put(kappa.getName().toLowerCase(),kappa);
		commands.put(triggered.getName().toLowerCase(),triggered);
		commands.put(help.getName().toLowerCase(),help);
		commands.put(ping.getName().toLowerCase(),ping);
		commands.put(burn.getName().toLowerCase(),burn);
		commands.put(gonbgud.getName().toLowerCase(),gonbgud);
		
		specialChatKeys.add(whoIsMatt.getName().toLowerCase());
		specialChatKeys.add(dinkster.getName().toLowerCase());
	}
}
