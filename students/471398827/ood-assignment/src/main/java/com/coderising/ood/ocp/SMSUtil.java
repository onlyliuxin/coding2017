package com.coderising.ood.ocp;

public class SMSUtil implements ILog{

	@Override
	public void printLog(String msg) {
	    msg = "SMS..." + "\n" + msg;
		System.out.println(msg);
	}

}
