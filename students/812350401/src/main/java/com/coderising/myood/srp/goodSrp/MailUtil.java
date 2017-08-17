package com.coderising.myood.srp.goodSrp;

public class MailUtil {

	public static void sendEmail(Mail mail, String smtpHost, String fromAddress) {
		//假装发了一封邮件
		System.out.println("使用smtpHost为"+smtpHost);
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(mail.getAddress()).append("\n");
		buffer.append("Subject:").append(mail.getSubject()).append("\n");
		buffer.append("Content:").append(mail.getBody()).append("\n");

		System.out.println(buffer.toString());
	}
}
