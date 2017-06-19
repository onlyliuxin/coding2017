package com.coderising.ood.ocp;

public class PrintLog implements LogMethod{

	@Override
	public void send(String msg) {
		System.out.println("Print Log "+msg);
	}

}
