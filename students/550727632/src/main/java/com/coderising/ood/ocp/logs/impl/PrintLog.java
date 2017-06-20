package com.coderising.ood.ocp.logs.impl;

import com.coderising.ood.ocp.logs.ILogMethod;

public class PrintLog implements ILogMethod {

	@Override
	public void sendLog(String msg) {
		System.out.println(msg);
	}

}
