package com.coderising.ood.ocp;

import com.coderising.ood.ocp.formatter.Formatter;
import com.coderising.ood.ocp.sender.Sender;

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

