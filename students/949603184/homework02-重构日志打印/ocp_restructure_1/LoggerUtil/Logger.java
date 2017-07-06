package com.coderising.ood.ocp.LoggerUtil;

import com.coderising.ood.ocp.Log.BaseLog;
import com.coderising.ood.ocp.Log.PrintLog;
import com.coderising.ood.ocp.MsgUtil.BaseMsgTool;
import com.coderising.ood.ocp.MsgUtil.HandleMsgWithNone;

public class Logger {

	private BaseMsgTool tool;
	private BaseLog log;

	public Logger(BaseMsgTool tool, BaseLog log) {
		this.tool = tool;
		this.log = log;
	}

	public void log(String msg) {
		log.sendLog(tool.handleMsg(msg));
	}
	
	public static void main(String[] args) {
		new Logger(new HandleMsgWithNone(), new PrintLog()).log("Hello world");
	}
}
