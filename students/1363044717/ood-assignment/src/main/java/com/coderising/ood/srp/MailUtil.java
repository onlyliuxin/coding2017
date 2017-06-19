package com.coderising.ood.srp;

public class MailUtil {

	public static void sendEmail(String toAddress, String fromAddress,Mail mail, String smtpHost,
			boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(mail.getSubject()).append("\n");
		buffer.append("Content:").append(mail.getMessage()).append("\n");
		System.out.println(buffer.toString());
	}

}
