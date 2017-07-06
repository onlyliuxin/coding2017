package com.coderising.ood.ocp.chasing;

import com.coderising.ood.ocp.chasing.formatter.ILogFormatter;
import com.coderising.ood.ocp.chasing.sender.ILogSender;

public class Logger {
	
	private ILogFormatter formatter;
	private ILogSender sender;
			
	public Logger(ILogFormatter formatter, ILogSender sender){
		this.formatter = formatter;
		this.sender = sender;		
	}
	public void log(String msg){
		sender.send(formatter.format(msg));
	}
}

