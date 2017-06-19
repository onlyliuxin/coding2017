package com.coderising.ood.srp;

import Model.Message;
import configurations.Configuration;
import configurations.ConfigurationKeys;
import utils.MailUtil;

public class Deliver {
	private String smtpHost;
	private String altSmtpHost;
	
	public Deliver() {
		smtpHost = Configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
		altSmtpHost = Configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
	}
	
	public void sendMails(Message message, Boolean debug) {
		try {
			if (message.hasValidAddress()) {
				MailUtil.sendEmail(message, smtpHost, debug);
			}
		} catch (Exception e) {
			//备用SMTP服务器
			MailUtil.sendEmail(message, altSmtpHost, debug);
		}
	}
}
