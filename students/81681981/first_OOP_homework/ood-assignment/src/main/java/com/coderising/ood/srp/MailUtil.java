package com.coderising.ood.srp;


public class MailUtil {
	public static void sendEmail(String toAddress,Message mes,boolean debug) {
		Email email = new Email();
				//假装发了一封邮件
				StringBuilder buffer = new StringBuilder();
				buffer.append("From:").append(email.getFromAddress()).append("\n");
				buffer.append("To:").append(toAddress).append("\n");
				buffer.append("Subject:").append(mes.getSubject()).append("\n");
				buffer.append("Content:").append(mes.getMessageDesc()).append("\n");
				System.out.println(buffer.toString());
			}
}
