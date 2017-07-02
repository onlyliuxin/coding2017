package com.coderising.ood.ocp.chasing.sender;

import com.coderising.ood.ocp.chasing.SMSUtil;

public class StdoutSender implements ILogSender {

	@Override
	public void send(String msg) {
		SMSUtil.send(msg);
	}

}
