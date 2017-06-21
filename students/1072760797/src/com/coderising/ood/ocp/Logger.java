package com.coderising.ood.ocp;

public abstract class Logger {
	

	public void log(String msg){
				
		setMsg(msg);
		
		sendMsg(msg);
	}
	
	public abstract void setMsg(String msg);
	public abstract void sendMsg(String logMsg);

}

