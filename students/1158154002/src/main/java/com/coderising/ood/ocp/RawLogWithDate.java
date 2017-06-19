package com.coderising.ood.ocp;

public class RawLogWithDate implements LogType {

	@Override
	public String getMsg(String msg) {
		String txtDate = DateUtil.getCurrentDateAsString();
		msg = txtDate + ": " + msg;
		return msg;
	}

}
