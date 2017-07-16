package com.coderising.ood.ocp.chasing.sender;

import com.coderising.ood.ocp.chasing.MailUtil;

public class MailSender implements ILogSender {

	@Override
	public void send(String msg) {
		MailUtil.send(msg);
	}

}
