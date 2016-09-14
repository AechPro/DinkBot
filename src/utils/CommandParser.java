package utils;
import java.util.ArrayList;

import net.dv8tion.jda.events.message.*;
public class CommandParser 
{
	public CommandContainer parse(String rw, MessageReceivedEvent e)
	{
		ArrayList<String> split = new ArrayList<String>();
		String raw = rw;
		String beheaded = raw.replaceFirst("!","");
		String[] splitBeheaded = beheaded.split(" ");
		for(String s : splitBeheaded){split.add(s);}
		String invoke = split.get(0);
		String[] args = new String[split.size()-1];
		split.subList(1,split.size()).toArray(args);
		return new CommandContainer(raw,beheaded,splitBeheaded,invoke,args,e);
	}
	public class CommandContainer
	{
		public final String RAW;
		public final String BEHEADED;
		public final String[] SPLITBEHEADED;
		public final String INVOKE;
		public final String[] ARGS;
		public final MessageReceivedEvent EVENT;
		public CommandContainer(String rw, String beh, String[] sBeh, String inv, String[] ar, MessageReceivedEvent e)
		{
			this.RAW=rw;
			this.BEHEADED=beh;
			this.SPLITBEHEADED = sBeh;
			this.INVOKE=inv;
			this.ARGS=ar;
			this.EVENT = e;
		}
	}
}
