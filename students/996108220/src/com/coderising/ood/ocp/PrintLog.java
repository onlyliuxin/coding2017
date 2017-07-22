package com.coderising.ood.ocp;

public class PrintLog implements LogMethod{
	int method = 3;
	@Override
	public void logBehavior(String logMsg) {
		System.out.println(logMsg);
		
	}

}
