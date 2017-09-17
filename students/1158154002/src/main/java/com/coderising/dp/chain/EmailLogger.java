package com.coderising.dp.chain;

public class EmailLogger extends Logger{

	EmailLogger(int level) {
		super(level);
	}

	@Override
	protected void write(String message) {
		System.out.println("EmailLogger "+message);
	}


}
