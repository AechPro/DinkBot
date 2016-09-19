package utils;

import net.dv8tion.jda.events.message.MessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;

//TODO: change this class to "Chat handler" and handle chat events and responses
public class ChatListener extends ListenerAdapter
{
	private static CommandParser parser;
	private static CommandHandler handler;
	public ChatListener(CommandParser pars, CommandHandler handle)
	{
		super();
		parser = pars;
		handler = handle;
	}
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		if(event.getAuthor().getId() != event.getJDA().getSelfInfo().getId())
		{
			System.out.println("MESSAGE RECEIVED");
			if(event.getMessage().getContent().startsWith(("!")))
			{
				System.out.println("passing message to handler");
				handler.handleCommand(parser.parse(event.getMessage().getContent().toLowerCase(), event));
			}
		}
	}
}
