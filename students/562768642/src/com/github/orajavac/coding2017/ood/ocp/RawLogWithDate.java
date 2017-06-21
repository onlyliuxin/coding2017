package com.github.orajavac.coding2017.ood.ocp;

public class RawLogWithDate implements RawLogger{
	public void log(String msg){
		String txtDate = DateUtil.getCurrentDateAsString();
		String logMsg = txtDate + ": " + msg;
	}
}
