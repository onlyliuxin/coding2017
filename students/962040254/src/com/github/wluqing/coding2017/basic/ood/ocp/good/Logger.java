package com.github.wluqing.coding2017.basic.ood.ocp.good;

public class Logger {
	
	private Formatter formatter;
	private Sender sender;
			
	public Logger(Formatter formatter,Sender sender){
		this.formatter = formatter;
		this.sender = sender;
	}
	public void log(String msg){
		sender.send(formatter.format(msg))	;	
	}
	
	
}

