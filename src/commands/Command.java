package commands;

import java.io.File;
import net.dv8tion.jda.entities.TextChannel;

public abstract class Command 
{
	protected String name;
	protected String message;
	protected File file;
	public abstract void sendMessage(TextChannel channel);
	public File getFile(){return file;}
	public String getName(){return name;}
	public String getMessage(){return message;}
	
	public void setFile(File i){file = i;}
	public void setName(String i){name=i;}
	public void setMessage(String i){message=i;}
}
