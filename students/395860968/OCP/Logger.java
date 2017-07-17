package com.company;

public class Logger {
	private AbstractNotifier notifier;
	private Formatter formatter;
	public Logger(Formatter formatter, AbstractNotifier notifier){
		this.formatter = formatter;
		this.notifier = notifier;
	}
	public void log(String msg){
		String logMsg = this.formatter.formatMessage(msg);
		notifier.send(logMsg);
	}
}

