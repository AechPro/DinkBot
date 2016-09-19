package commands;

import java.io.File;

import net.dv8tion.jda.entities.TextChannel;

public class DefaultCommand extends Command
{
	public DefaultCommand(String m, File f, String n, int maxU){super(m, f, n, maxU);}

	@Override
	public void giveInput(TextChannel channel, String[] args) {}

	@Override
	public boolean create(String[] args) {return true;}
}
