package com.coderising.ood.ocp;

public class MailUtil implements ILog{

	@Override
	public void printLog(String msg) {
		msg = "Mail..." + "\n" + msg;
		System.out.println(msg);
	}
}
