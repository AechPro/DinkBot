package utils;

import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

//TODO: change this class to "Chat handler" and handle chat events and responses
public class ChatListener extends ListenerAdapter
{
	private static CommandParser parser;
	private static CommandList commands;
	public ChatListener(CommandParser pars, CommandList comms)
	{
		super();
		commands = comms;
		parser = pars;
	}
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		if(event.getAuthor().getId() != event.getJDA().getSelfInfo().getId())
		{
			if(event.getMessage().getContent().toLowerCase().startsWith("!"))
			{
				commands.handleCommand(parser.parse(event.getMessage().getContent(), event));
			}
			else
			{
				commands.handleCommand(event.getMessage().getContent().toLowerCase(),event);
			}
		}
	}
}
