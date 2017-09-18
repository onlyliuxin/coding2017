package com.coderising.dp.chain;

public abstract class Logger {
	protected Logger nextLogger;
	protected int type;
	
	
	public static final int DEBUG = 1;
	public static final int NOTICE = 2;
	public static final int ERR = 3;

	public abstract Logger setNext(Logger logger);

	public abstract void message(String message, int type);

	protected boolean hasType(int type) {
		// TODO Auto-generated method stub
		return this.type == type;
	}
}
