package com.coderising.ood.ocp;

public class Logger {

	int type = 0;
	int method = 0;

	public Logger(int logType, int logMethod) {
		this.type = logType;
		this.method = logMethod;
//		System.out.println("this type is " + this.type + ", this method is " + this.method);
	}

	public void log(String msg) {

		String logMsg = RAWFactory.getRAW(this.type, msg);
		BaseUtil util = LoggerFactory.getLogger(this.method);
		if (util != null) {
			util.send(logMsg);
		} else {
			System.out.println("Not Found method :" + this.method);
		}
	}
}
