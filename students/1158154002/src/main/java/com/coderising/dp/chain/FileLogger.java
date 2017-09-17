package com.coderising.dp.chain;

public class FileLogger extends Logger {

	FileLogger(int level) {
		super(level);
	}

	@Override
	protected void write(String message) {
		System.out.println("FileLogger " + message);
	}


}
