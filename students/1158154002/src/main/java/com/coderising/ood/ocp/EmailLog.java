package com.coderising.ood.ocp;

public class EmailLog implements LogMethod{

	@Override
	public void send(String msg) {
		System.out.println("Email send "+msg);
	}

}
