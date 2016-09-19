package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import commands.*;

public class CommandList 
{
	private static HashMap<String, Command> commandMap;
	private static HashMap<String, PlaylistCommand> playlistMap;
	private static Playlist currentPlaylist;
	private static VoteCommand vote;
	private static RollCommand roller;
	public CommandList()
	{
		currentPlaylist = null;
		vote = null;
		playlistMap = new HashMap<String, PlaylistCommand>();
		fillCommandList();
	}
	
	public Command findCommand(String key)
	{
		if(!commandMap.containsKey(key)){return null;}
		return commandMap.get(key);
	}
	public PlaylistCommand findPlaylistCommand(String key)
	{
		return playlistMap.get(key);
	}
	public VoteCommand getCurrentVote(){return vote;}
	public RollCommand getCurrentRoll(){return roller;}
	public void setVote(VoteCommand i){vote=i;}
	public void addPlaylist(String[] args)
	{
		PlaylistCommand command = new PlaylistCommand(null,null,args[1],1);
		if(command.setPlaylist(args))
		{
			playlistMap.put(args[1], command);
			System.out.println("PLAYLIST CREATED");
			return;
		}
		System.out.println("FAILED TO CREATE PLAYLIST");
	}
	private void fillCommandList()
	{
		commandMap = new HashMap<String, Command>();
		
		String helpString = "The currently available commands are: \n !help \n !burn \n !kappa \n !ping \n !gonbgud \n !triggered";
		
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
	
	public Playlist getPlaylist(String key){return playlistMap.get(key).getPlaylist();}
	public Playlist getCurrentPlaylist(){return currentPlaylist;}
	public void setCurrentPlaylist(Playlist i){currentPlaylist = i;}
	public void setCurrentRoller(RollCommand i){roller = i;}
}
