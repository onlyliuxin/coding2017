package com.coderising.dp.chain;

public class ChainLogger {
	public static void main(String[] args) {
		Logger logger = new StdoutLogger(Logger.DEBUG)
				.setNext(new EmailLogger(Logger.NOTICE).setNext(new FileLogger(Logger.ERR)));
		logger.message("?????????", Logger.DEBUG);
		logger.message("???????????", Logger.NOTICE);
		logger.message("????????????????", Logger.ERR);
	}
}
