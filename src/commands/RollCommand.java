package commands;

import java.io.File;

import net.dv8tion.jda.entities.TextChannel;

public class RollCommand extends Command
{
	private int result;
	public RollCommand(String m, File f, String n, int maxU) 
	{
		super(m, f, n, maxU);
	}
	@Override
	public void giveInput(TextChannel channel, String[] args)
	{
		message = "Invalid dice roll, try again!";
		if(Integer.parseInt(args[0])<=15)
		{
			result = 0;
			int cRoll = 0;
			int[] vals = new int[Integer.parseInt(args[0])];
			String num = args[1].replace("d", "");
			if(Integer.parseInt(num)<0)
			{
				message = "No you can't roll negative dice, Jack.";
				return;
			}
			for(int i=0,stop=Integer.parseInt(args[0]);i<stop;i++)
			{
				cRoll = roll(Integer.parseInt(num));
				result += cRoll;
				vals[i] = cRoll;
			}
			if(args.length>2){result+=Integer.parseInt(args[2]);}
			message = "ROLL RESULTS:\n";
			for(int i=0;i<vals.length;i++)
			{
				message+="ROLL "+(i+1)+" = "+vals[i]+"\n";
			}
			message+="TOTAL OF ALL ROLLS = "+result;
		}
	}
	public int roll(int dNum)
	{
		return (int)(Math.random()*dNum)+1;
	}
	@Override
	public boolean create(String[] args) {return true;}

}
