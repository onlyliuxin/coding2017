package com.coderising.ood.ocp;

public class SmsLog implements LogMethod{

	@Override
	public void send(String msg) {
		System.out.println("SMS send "+msg);
	}

}
