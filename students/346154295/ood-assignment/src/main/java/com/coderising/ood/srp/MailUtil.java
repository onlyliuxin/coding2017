package com.coderising.ood.srp;

public class MailUtil {
	private static String smtpHost;
	private static String altSmtpHost;
	private static String fromAddress;

	public static void init() {
		Configuration config = new Configuration();
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
		fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}

	private static void sendEmail(String toAddress, String subject, String message, String smtpHost,
			boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());
		
	}
	public static void sendEmail(String toAddress, String subject, String message, boolean debug) {
		if(toAddress == null || toAddress.length() == 0) {
			return;
		}
		try {
			sendEmail(toAddress,subject,message,smtpHost,debug);
		} catch (Exception e) {
			try {
				sendEmail(toAddress, subject, message, altSmtpHost, debug);
			} catch (Exception e2){
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
			}
		}
	}
}
