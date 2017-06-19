package com.coderising.ood.ocp.logs.impl;

import com.coderising.ood.ocp.logs.ILogMethod;
import com.coderising.ood.ocp.util.MailUtil;

public class EmailLog implements ILogMethod {

	@Override
	public void sendLog(String msg) {
		MailUtil.send(msg);
	}

}
