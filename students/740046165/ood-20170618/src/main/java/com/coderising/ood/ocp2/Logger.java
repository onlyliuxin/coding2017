package com.coderising.ood.ocp2;

import com.coderising.ood.ocp.MailUtil;

public class  Logger {
	
	//工厂
	
	public void send(String logMsg) {
		MailUtil.send(logMsg);
	}

}
