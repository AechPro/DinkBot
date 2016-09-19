package commands;

import java.io.File;

import utils.Playlist;

import net.dv8tion.jda.entities.TextChannel;

public class PlaylistCommand extends ParameterCommand
{
	private static Playlist playlist;
	public PlaylistCommand(String m, File f, String n, int maxU)
	{
		super(m, f, n, maxU);
	}
	
	
	public void giveInput(TextChannel chann, String[] args)
	{
		switch(args[1])
		{		
			case "play":
				if(playlist != null)
				{
					chann.sendMessage("Playing "+playlist.getName()+".");
					playlist.play(chann);
				}
				break;
			case "pause":
				if(playlist != null)
				{
					chann.sendMessage("Playlist "+playlist.getName()+" paused.");
					playlist.pause();
				}
				break;
			case "restart":
				if(playlist != null)
				{
					chann.sendMessage("Playlist "+playlist.getName()+" restarted.");
					playlist.restart();
				}
				break;
			case "random":
				if(playlist != null)
				{
					chann.sendMessage("Random playlist selection:");
					playlist.random(chann);
				}
				break;
			case "shuffle":
				if(playlist != null)
				{
					chann.sendMessage("Playlist "+playlist.getName()+" shuffled.");
					playlist.shuffle();
				}
				break;
		}
	}
	public boolean setPlaylist(String[] args)
	{
		playlist = new Playlist(args[1], args[2], Integer.parseInt(args[3]));
		return playlist != null;
	}
	public void setPlaylist(Playlist i){playlist = i;}
	public Playlist getPlaylist(){return playlist;}
}
