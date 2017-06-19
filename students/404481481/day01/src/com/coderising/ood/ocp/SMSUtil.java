package com.coderising.ood.ocp;

public class SMSUtil implements BaseUtil {

	@Override
	public void send(String logMsg) {
		System.out.println("send SMS msg :" + logMsg);
	}

}
