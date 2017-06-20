package com.coderising.ood.ocp.logs.impl;

import com.coderising.ood.ocp.logs.ILogMethod;
import com.coderising.ood.ocp.util.SMSUtil;

public class SMSLog implements ILogMethod {

	@Override
	public void sendLog(String msg) {
		SMSUtil.send(msg);
	}

}
