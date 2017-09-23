package com.coderising.ood.ocp;

public class SMSLogWay implements ILogWay {
	@Override
	public void excutelog(String logMsg) {
		// TODO Auto-generated method stub
		SMSUtil.send(logMsg);
	}
}
