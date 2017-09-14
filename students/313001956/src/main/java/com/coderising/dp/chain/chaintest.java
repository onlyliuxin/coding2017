package com.coderising.dp.chain;

import static org.junit.Assert.*;

import org.junit.Test;

public class chaintest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		Logger logger = new StdoutLogger(Logger.DEBUG)
				.setNext(new EmailLogger(Logger.NOTICE).setNext(new FileLogger(Logger.ERR)));
		logger.message("?????????", Logger.DEBUG);
		logger.message("???????????", Logger.NOTICE);
		logger.message("????????????????", Logger.ERR);
	}

}
