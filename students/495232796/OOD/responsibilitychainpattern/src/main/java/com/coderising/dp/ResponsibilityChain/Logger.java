package com.coderising.dp.ResponsibilityChain;

public interface Logger {
	public static final int DEBUG = 0;
	public static final int NOTICE = 1;
	public static final int ERR = 2;
	
	public void message(String context, int level);
	public void setNext(Logger next);
}
