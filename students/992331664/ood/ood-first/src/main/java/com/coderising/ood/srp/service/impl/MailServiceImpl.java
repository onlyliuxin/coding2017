package com.coderising.ood.srp.service.impl;

import com.coderising.ood.srp.config.Configuration;
import com.coderising.ood.srp.config.ConfigurationKeys;
import com.coderising.ood.srp.model.MailInfo;
import com.coderising.ood.srp.service.MailService;
import com.coderising.ood.srp.util.MailUtil;

public class MailServiceImpl implements MailService {

	private String fromAddress;
	private String smtpHost;
	private String altSmtpHost;

	public MailServiceImpl(Configuration config) {
		this.fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
		this.smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
		this.altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
	}

	public void sendMail(MailInfo mail) {
		try {
			sendEmail(mail, this.smtpHost);
		} catch (Exception e) {
			try {
				sendEmail(mail, this.altSmtpHost);
			} catch (Exception ex) {
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + ex.getMessage());
			}

		}
	}

	private void sendEmail(MailInfo mail, String smtpHost) {
		String toAddress = mail.getToAddress();
		String subject = mail.getSubject();
		String message = mail.getMessage();
		MailUtil.sendEmail(toAddress, fromAddress, subject, message, smtpHost, false);
		// 发送邮件
	}

}
