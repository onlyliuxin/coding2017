package com.coderising.ood.ocp;

public class EmailLogger extends Logger{
	
	public void log(String msg){
		MailUtil.send(msg);
	}

}
