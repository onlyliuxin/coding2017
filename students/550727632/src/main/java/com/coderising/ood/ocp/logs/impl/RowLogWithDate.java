package com.coderising.ood.ocp.logs.impl;

import com.coderising.ood.ocp.logs.ILogType;

public class RowLogWithDate implements ILogType {

	@Override
	public String formatMessage(String msg) {
		return msg;
	}

}
