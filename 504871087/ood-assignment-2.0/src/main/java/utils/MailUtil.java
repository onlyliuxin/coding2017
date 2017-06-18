package utils;

import Model.Message;

public class MailUtil {

	public static void sendEmail(Message message, String host, boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(message.getFromAddress()).append("\n");
		buffer.append("To:").append(message.getToAddress()).append("\n");
		buffer.append("Subject:").append(message.getSubject()).append("\n");
		buffer.append("Content:").append(message.getMessage()).append("\n");
		System.out.println(buffer.toString());
	}
}
