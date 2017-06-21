package com.coderising.ood.ocp;

public class Logger {
	int type = 0;
	int method = 0;
			
	public Logger(int logType, int logMethod){
		this.type = logType;
		this.method = logMethod;		
	}
	public void log(String msg){
		
		String logMsg = msg;
		
		if(this.type == LogType.RAW_LOG){
			logMsg = msg;
		} else if(this.type == LogType.RAW_LOG_WITH_DATE){
			String txtDate = DateUtil.getCurrentDateAsString();
			logMsg = txtDate + ": " + msg;
		}
		
		SenderFactory.createSender(type).send(logMsg);
	}
}

