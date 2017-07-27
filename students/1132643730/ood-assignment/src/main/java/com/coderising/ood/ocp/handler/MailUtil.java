package com.coderising.ood.ocp.handler;

public class MailUtil implements LogHandler{

	public void handleLog(String logMsg) {
		System.out.println("MailUtil handle, msg= "+ logMsg);
	}

}
