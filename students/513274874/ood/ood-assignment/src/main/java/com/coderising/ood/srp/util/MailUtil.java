package com.coderising.ood.srp.util;

import com.coderising.ood.srp.Configuration;
import com.coderising.ood.srp.dto.Mail;

public class MailUtil {

	public static void sendEmail(Mail mail,Configuration config,
			boolean debug) {

		StringBuilder buffer = new StringBuilder();
		try {
			//假装发了一封邮件
			buffer.append("With SmtpHost:").append(config.getSmtpHost()).append("\n");

		}catch (Exception e){
			try {
				//假装发了一封邮件
				buffer.append("With AltSmtpHost:").append(config.getAltSmtpHost()).append(":\n");

			} catch (Exception e2)
			{
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
			}
		}

		buffer.append("From:").append(config.getFromAddress()).append("\n");
		buffer.append("To:").append(mail.getToAddress()).append("\n");
		buffer.append("Subject:").append(mail.getSubject()).append("\n");
		buffer.append("Content:").append(mail.getMessage()).append("\n");

		System.out.println(buffer.toString());
		
	}

	
}
