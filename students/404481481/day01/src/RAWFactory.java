package com.coderising.ood.ocp;

public class RAWFactory {

	public final static int RAW_LOG = 1;
	public final static int RAW_LOG_WITH_DATE = 2;

	public static String getRAW(int logType, String msg) {
		String logMsg = msg;
		switch (logType) {
		case RAW_LOG:
			logMsg = msg;
			break;
		case RAW_LOG_WITH_DATE:
			logMsg = DateUtil.getCurrentDateAsString() + ":" + msg;
			break;
		default:
			logMsg = msg;
			break;
		}
		return logMsg;
	}
}
