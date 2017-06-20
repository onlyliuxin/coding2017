package com.coderising.ood.srp;

public class MailUtil {

	public static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
			boolean debug) {
		if(toAddress==null||toAddress.equals("")){
			throw new RuntimeException("发送地址不能为空！");
		}
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());
		
	}

	
}
