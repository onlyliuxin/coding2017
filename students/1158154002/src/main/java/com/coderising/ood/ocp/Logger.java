package com.coderising.ood.ocp;

public class Logger {

	LogType type;
	LogMethod method;

	public Logger(LogType logType, LogMethod logMethod) {
		this.type = logType;
		this.method = logMethod;
	}

	public void log(String msg) {
		method.send(type.getMsg(msg));
	}
	
	public static void main(String[] args) {
		Logger logger=new Logger(new RawLog(), new EmailLog());
		logger.log("hello world !");
	}
}
