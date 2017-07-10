package com.coderising.ood.ocp;

public class Logger {
	
	private Formatter formatter;
	private Dilever deliver;
	public Logger(Formatter formatter, Dilever deliver)
	{
		this.formatter = formatter;
		this.deliver = deliver;		
	}
	public void log(String msg)
	{		
		String message = formatter.formate(msg);
		deliver.process(message);
	}
}

