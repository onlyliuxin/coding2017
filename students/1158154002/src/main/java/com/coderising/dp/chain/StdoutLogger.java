package com.coderising.dp.chain;

public class StdoutLogger extends Logger{

	StdoutLogger(int level) {
		super(level);
	}

	@Override
	protected void write(String message) {
		System.out.println("StdoutLogger "+message);
	}
}
