package com.coderising.ood.ocp.logs;

public interface ILogType {

	/**
	 * 格式化log信息
	 * 
	 * @param msg
	 * @return
	 */
	String formatMessage(String msg);
}
