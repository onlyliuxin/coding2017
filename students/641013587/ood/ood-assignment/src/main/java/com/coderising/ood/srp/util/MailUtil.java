package com.coderising.ood.srp.util;

import com.coderising.ood.srp.entity.Msg;

public class MailUtil {

	public static void sendEmail(
			boolean debug,Msg msg) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(msg.getFromAddress()).append("\n");
		buffer.append("To:").append(msg.getToAddress()).append("\n");
		buffer.append("Subject:").append(msg.getSubject()).append("\n");
		buffer.append("Content:").append(msg.getMessage()).append("\n");
		System.out.println(buffer.toString());
		
	}

	
}
