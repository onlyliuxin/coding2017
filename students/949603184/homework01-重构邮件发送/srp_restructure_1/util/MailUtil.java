package com.coderising.ood.srp_restructure_1.util;

import com.coderising.ood.srp_restructure_1.pojo.Mail;
import com.coderising.ood.srp_restructure_1.pojo.MailServiceConfiguration;

public class MailUtil {

	private static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
			boolean debug) {
		// 假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());
	}

	public static void sendEmail(boolean debug, MailServiceConfiguration configuration, Mail mail) {
		try {
			if (mail.getToAddress().length() > 0)
				sendEmail(mail.getToAddress(), configuration.getFromAddress(), mail.getSubject(), mail.getMessage(),
						configuration.getSMTPHost(), debug);
		} catch (Exception e) {
			try {
				sendEmail(mail.getToAddress(), configuration.getFromAddress(), mail.getSubject(), mail.getMessage(),
						configuration.getAltSMTPHost(), debug);

			} catch (Exception e2) {
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
			}
		}
	}
}