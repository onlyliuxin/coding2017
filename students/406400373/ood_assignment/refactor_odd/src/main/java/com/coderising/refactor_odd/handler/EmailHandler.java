package com.coderising.refactor_odd.handler;

import com.coderising.refactor_odd.entity.EmailEntity;
import org.apache.commons.lang3.StringUtils;

import com.coderising.ood.srp.Configuration;
import com.coderising.ood.srp.ConfigurationKeys;
import com.coderising.ood.srp.MailUtil;

/**
 * @author cenkailun
 * @Date 17/6/16
 * @Time 下午5:40
 */
public class EmailHandler {

	private String smtpHost = null;
	private String altSmtpHost = null;
	private String fromAddress = null;

	public EmailHandler() {
		initEmailHandler();
	}

	private void initEmailHandler() {
		Configuration configuration = new Configuration();
		smtpHost = configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
		altSmtpHost = configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
		fromAddress = configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}

	public void sendMail(EmailEntity emailEntity, boolean debug) {
		// TODO 校验
		send(emailEntity.getToAddress(),
				StringUtils.isEmpty(emailEntity.getFromAddress()) ? this.fromAddress : emailEntity.getFromAddress(),
				emailEntity.getSubject(), emailEntity.getMessage(), debug);
	}

	private void send(String toAddress, String fromAddress, String subject, String message, boolean debug) {
		try {
			if (toAddress.length() > 0)
				MailUtil.sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);
		} catch (Exception e) {
			try {
				MailUtil.sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug);
			} catch (Exception e2) {
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
			}
		}
	}
}
