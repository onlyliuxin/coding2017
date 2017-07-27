package com.coderising.ood.ocp;

public class RawLog implements LogType{
	int type = 1;
	@Override
	public String getLogMsg(String msg) {
		return msg;
	}

}
