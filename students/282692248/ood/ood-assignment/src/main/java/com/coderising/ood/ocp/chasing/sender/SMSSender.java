package com.coderising.ood.ocp.chasing.sender;

public class SMSSender implements ILogSender {

	@Override
	public void send(String msg) {
		System.out.println(msg);
	}

}
