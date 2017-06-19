package com.coderising.ood.ocp;

public class MailUtil implements BaseUtil {

	@Override
	public void send(String logMsg) {
		System.out.println("send Mail msg : " + logMsg);
	}

}
