package com.coderising.ocp;

public class NormalLogPrinter implements LogPrinter {

	@Override
	public void print(String log) {
		System.out.println(log);
	}

}
