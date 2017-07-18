package com.coderising.ood.srp;

public class MailUtil {

	public static void sendEmail(MailAddr mailAddr, MailMsg mailMsg, boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(mailAddr.getFromAddress()).append("\n");
		buffer.append("To:").append(mailAddr.getToAddress()).append("\n");
		buffer.append("Subject:").append(mailMsg.getSubject()).append("\n");
		buffer.append("Content:").append(mailMsg.getMessage()).append("\n");
		System.out.println(buffer.toString());
	}
}
