package com.coderising.ood.srp.util;

public class MailUtil {




	public static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
			boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());

	}

	public static void sendEmail(String toAddress, String fromAddress, String subjuect, String message, String smtpHost, String altSmtpHost, boolean debug){
		try{
			sendEmail(toAddress, fromAddress, subjuect, message, smtpHost, debug);
		}catch (Exception e){
			try{
				sendEmail(toAddress, fromAddress, subjuect, message, altSmtpHost ,debug);
			}catch (Exception e1){
				System.out.println("发送邮件失败！");
			}
		}
	}

	
}
