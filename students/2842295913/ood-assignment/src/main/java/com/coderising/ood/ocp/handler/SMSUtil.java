package com.coderising.ood.ocp.handler;

public class SMSUtil implements LogHandler{

	public void handleLog(String logMsg) {
		System.out.println("SMSUtil handle, msg= "+ logMsg);
	}

}
