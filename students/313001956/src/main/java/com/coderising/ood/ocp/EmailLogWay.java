package com.coderising.ood.ocp;

public class EmailLogWay implements ILogWay {
	public void excutelog(String logMsg) {
		MailUtil.send(logMsg);
	}
}
