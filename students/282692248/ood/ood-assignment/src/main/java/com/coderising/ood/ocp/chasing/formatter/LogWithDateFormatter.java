package com.coderising.ood.ocp.chasing.formatter;

import com.coderising.ood.ocp.chasing.DateUtil;

public class LogWithDateFormatter implements ILogFormatter {

	@Override
	public String format(String txt) {
		return DateUtil.getCurrentDateAsString() + ": " + txt;
	}

}
