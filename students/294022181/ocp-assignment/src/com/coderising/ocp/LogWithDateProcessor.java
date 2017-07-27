package com.coderising.ocp;

import java.util.Date;

public class LogWithDateProcessor implements LogProcessor {

	@Override
	public String process(String msg) {
		String txtDate = new Date().toString();
		return txtDate + ": " + msg;
	}
}
