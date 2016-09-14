package main;

import net.dv8tion.jda.*;
import utils.*;

public class Main
{
	private JDABuilder bot;
	public static final CommandParser PARSER = new CommandParser();
	private static final ChatListener LISTENER = new ChatListener(PARSER);
	public Main()
	{
		
		bot = new JDABuilder();
		try 
		{
			bot.addListener(LISTENER);
			bot.setBotToken("MjI1MzY5OTE4MjUyNjQ2NDAx.CroDtw.bLwog-OoOKUZcX5S3MlgrQhTTLw").buildBlocking();
			bot.setAutoReconnect(true);
			System.out.println("finished building");
		}
		catch (Exception e){e.printStackTrace();}
		System.out.println("DID SOMEBODY RING THE DINKSTER?");
		
		/*commands.put("ping", new PingCommand());
		commands.put("kappa", new KappaCommand());
		commands.put("burn", new BurnCommand());
		commands.put("help", new HelpCommand());
		commands.put("triggered", new TriggeredCommand());
		commands.put("gonbgud", new GonBGudCommand());
		//commands.put("meme", new MemeCommand());*/
		
	}
	
	

	public static void main(String[] args) 
	{
		new Main();
	}

}
