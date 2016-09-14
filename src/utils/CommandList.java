package utils;

import java.util.HashMap;

import commands.Command;

public class CommandList 
{
	public static HashMap<String, Command> commands;
	public CommandList(){commands = new HashMap<String, Command>();}
	public static void handleCommand(CommandParser.CommandContainer cmd)
	{
		System.out.println("handle command with "+cmd.INVOKE);
		if(commands.containsKey(cmd.INVOKE))
		{
			System.out.println("key "+cmd.INVOKE+" found");
			commands.get(cmd.INVOKE).sendMessage(cmd.EVENT.getTextChannel());
		}
	}
}
