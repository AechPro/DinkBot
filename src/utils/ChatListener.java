package utils;

import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

//TODO: change this class to "Chat handler" and handle chat events and responses
public class ChatListener extends ListenerAdapter
{
	private static CommandParser parser;
	public ChatListener(CommandParser pars)
	{
		super();
		parser = pars;
	}
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		
	}
}
