package com.coderising.ood.ocp;

public class Logger {

	private IMessageWay messageWay;
	private ILogWay logWay;

	public Logger(IMessageWay messageWay, ILogWay logWay) {
		this.messageWay = messageWay;
		this.logWay = logWay;
	}

	public void log(String msg) {	

		logWay.excutelog(messageWay.getmessage(msg));
	}
}
