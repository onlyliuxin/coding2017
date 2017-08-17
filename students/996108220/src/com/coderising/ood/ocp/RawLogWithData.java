package com.coderising.ood.ocp;

public class RawLogWithData implements LogType{
	int type = 2;
	@Override
	public String getLogMsg(String msg) {
		String txtDate = DateUtil.getCurrentDateAsString();
		String logMsg = txtDate + ": " + msg;
		return logMsg;
	}

}
