package com.coderising.ood.srp.util;

/**
 * 邮件发送工具类
 */
public class MailUtil {
	public static void sendEmail(Email email, boolean debug) {
		// 假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(email.getFromAddress()).append("\n");
		buffer.append("To:").append(email.getToAddress()).append("\n");
		buffer.append("Subject:").append(email.getSubject()).append("\n");
		buffer.append("Content:").append(email.getMessage()).append("\n");
		System.out.println(buffer.toString());
	}

}
