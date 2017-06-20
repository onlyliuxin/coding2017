package com.coderising.ood.ocp;

public class Logger {
	ILog myLog;
	IMessage myMessage;
			
	public Logger(int logType, int logMethod){
		myMessage = MessageFactory.produce(logType);
		myLog = LogFactory.produce(logMethod);
	}
	public void log(String msg){

		myLog.printLog(myMessage.getMessage(msg));

	}

	public static void main(String[] args) {
		Logger logger = new Logger(Config.RAW_LOG_WITH_DATE, Config.EMAIL_LOG);
		logger.log("this is a log message");
	}
}

