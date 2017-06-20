package com.coderising.ood.ocp;

import com.coderising.ood.ocp.logs.ILogMethod;
import com.coderising.ood.ocp.logs.ILogType;

public class Logger {

	private ILogType logType;
	private ILogMethod logMethod;

	public Logger(ILogType logType, ILogMethod logMethod) {
		this.logType = logType;
		this.logMethod = logMethod;
	}

	public void log(String msg) {
		String logMsg = this.logType.formatMessage(msg);
		this.logMethod.sendLog(logMsg);
	}
}
