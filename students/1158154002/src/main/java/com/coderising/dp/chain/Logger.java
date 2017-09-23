package com.coderising.dp.chain;

public abstract class Logger {
	public static int DEBUG = 1;
	public static int NOTICE = 2;
	public static int ERR = 3;
	protected int level;
	
	Logger(int level) {
		this.level=level;
	}
	protected Logger nextLogger;
	public Logger setNextLogger(Logger nextLogger) {
		 this.nextLogger = nextLogger;
		 return this;
	}
	
	public void message(String message,int level){
		if (this.level<=level) {
			write(message);
		}
		if (nextLogger!=null) {
			nextLogger.message( message,level);
		}
	}
	abstract protected void write(String message);
	
}
