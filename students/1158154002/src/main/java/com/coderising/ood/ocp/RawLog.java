package com.coderising.ood.ocp;

public class RawLog implements LogType {

	@Override
	public String getMsg(String msg) {
		return msg;
	}

}
