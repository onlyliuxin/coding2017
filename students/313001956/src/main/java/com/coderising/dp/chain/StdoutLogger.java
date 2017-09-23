package com.coderising.dp.chain;

public class StdoutLogger extends Logger {

	public StdoutLogger(int type) {
		this.type = type;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Logger setNext(Logger logger) {
		this.nextLogger = logger;
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void message(String message, int type) {
		// TODO Auto-generated method stub
		setMessage(message);
		if (!hasType(type)) {
			nextLogger.message(message, type);
		}
	}



	private void setMessage(String message) {
		System.out.println(message + "  type:" + type);
	}
}
