package com.coderising.ood.ocp;

public class EmailLog implements LogMethod{
	int method = 1;
	@Override
	public void logBehavior(String logMsg) {
		
		MailUtil.send(logMsg);
	}

}
