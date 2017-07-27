package com.coderising.ood.ocp.formatter;

import java.util.Date;

public class DateUtil implements LogFormatter{

	private String getCurrentDateAsString() {
		
		return "current date: "+ new Date();
	}

	/* (non-Javadoc)
	 * @see com.coderising.ood.ocp.LogFormatter#formatMsg(java.lang.String)
	 */
	@Override
	public String formatMsg(String msg) {
		return getCurrentDateAsString()+ ", "+ msg;
	}

}
