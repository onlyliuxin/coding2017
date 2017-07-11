package com.coderising.ood.ocp;

public class Logger {
	
	public LogType logType;
	public LogMethod logMethod;
			
	public Logger(LogType logType, LogMethod logMethod){
		this.logType = logType;
		this.logMethod = logMethod;		
	}
	public void log(String msg){
		
		String logMsg = logType.getLogMsg(msg);
		logMethod.logBehavior(logMsg);
		
	}
}

