package com.coderising.myood.ocp.myocp;

public class Logger {

	Sender sender;
	Formatter formatter;
			
	public Logger(Formatter aFormatter, Sender aSender){
		formatter = aFormatter;
		sender = aSender;
	}
	public void log(String message){
        sender.send(formatter.format(message));
	}
}

