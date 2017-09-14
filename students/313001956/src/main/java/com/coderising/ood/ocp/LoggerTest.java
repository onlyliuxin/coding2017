package com.coderising.ood.ocp;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoggerTest {

	@Test
	public void testLog() {
		Logger logger =new Logger(new EmailMessageWay(), new EmailLogWay());
		logger.log("hellow world");
		
		logger =new Logger(new SMSMessageWay(), new SMSLogWay());
		logger.log("hellow world");
		
		logger =new Logger(new PrintMessageWay(), new PrintLogWay());
		logger.log("hellow world");
	}

}
