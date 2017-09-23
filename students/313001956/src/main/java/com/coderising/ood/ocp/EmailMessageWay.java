package com.coderising.ood.ocp;

public class EmailMessageWay implements IMessageWay {
	public String getmessage(String msg) {
		String txtDate = DateUtil.getCurrentDateAsString();
		return txtDate + ": " + msg;
	}
}
