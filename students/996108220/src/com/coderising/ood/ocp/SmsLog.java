package com.coderising.ood.ocp;

public class SmsLog implements LogMethod{
	int method = 2;
	@Override
	public void logBehavior(String logMsg) {
		SMSUtil.send(logMsg);
	}

}
