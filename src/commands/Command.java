package commands;

import java.io.File;

import net.dv8tion.jda.entities.TextChannel;

/*TODO: add several outputs that can be selected at random, in an order or shuffled
 * add ability to pass parameters to the command indicating which message to send
 * */
public abstract class Command
{
	protected int maxUses;
	protected int uses;
	protected String name;
	protected String message;
	protected File file;
	protected long t0;
	public Command(String m, File f, String n, int maxU)
	{
		t0 = System.nanoTime();
		uses = 0;
		maxUses = maxU;
		message = m;
		if(m.equals("")){message = null;}
		file = f;
		name = n;
	}
	public void sendMessage(TextChannel channel)
	{
		if((System.nanoTime()-t0)/1000000>=1)
		{
			t0 = System.nanoTime();
			uses=0;
		}
		if(uses>maxUses)
		{
			if(message != null)
			{
				channel.sendMessage(message);
			}
			if(file != null)
			{
				channel.sendFile(file, null);
			}
		}
	}
	public File getFile(){return file;}
	public String getName(){return name;}
	public String getMessage(){return message;}
	
	public void setFile(File i){file = i;}
	public void setName(String i){name=i;}
	public void setMessage(String i){message=i;}
}
