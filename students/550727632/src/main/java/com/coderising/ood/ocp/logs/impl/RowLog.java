package com.coderising.ood.ocp.logs.impl;

import com.coderising.ood.ocp.logs.ILogType;
import com.coderising.ood.ocp.util.DateUtil;

public class RowLog implements ILogType {

	@Override
	public String formatMessage(String msg) {
		return DateUtil.getCurrentDateAsString() + ":" + msg;
	}

}
