package com.coderising.ood.ocp;

public class PrintLogWay implements ILogWay {
	@Override
	public void excutelog(String logMsg) {
		// TODO Auto-generated method stub
		System.out.println("Print:" + logMsg);
	}
}
