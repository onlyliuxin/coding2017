package com.coderising.ood.ocp;

public class RAW_Logger extends Logger {

	@Override
	public void setMsg(String msg) {
		// TODO Auto-generated method stub
		String logMsg = msg;
		logMsg = msg;
	}

	@Override
	public void sendMsg(String logMsg) {
		// TODO Auto-generated method stub
		MailUtil.send(logMsg);
	}

}
