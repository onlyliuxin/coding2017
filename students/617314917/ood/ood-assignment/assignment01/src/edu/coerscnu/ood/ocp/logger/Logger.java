package edu.coerscnu.ood.ocp.logger;

import edu.coerscnu.ood.ocp.logger.method.LogMethod;
import edu.coerscnu.ood.ocp.logger.type.LogType;

public class Logger {

	public LogType logType; // 日志类型
	public LogMethod logMethod; // 日志方法

	public Logger(LogType logType, LogMethod logMethod) {
		this.logType = logType;
		this.logMethod = logMethod;
	}

	public void log(String msg) {
		String logMsg = msg;
		logMsg = logMsg + logType.getMsg();
		logMethod.send(logMsg);
	}
}
