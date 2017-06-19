package com.coderising.ood.ocp.MsgUtil;

import com.coderising.ood.ocp.Util.DateUtil;

public class HandleMsgWithDate extends BaseMsgTool {
	public String handleMsg(String msg) {
		return DateUtil.getCurrentDateAsString() + ": " + msg;
	}
}
