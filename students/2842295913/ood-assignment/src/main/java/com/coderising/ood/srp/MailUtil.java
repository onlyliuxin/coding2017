package com.coderising.ood.srp;

public class MailUtil {

	private static String fromAddress;
	private static String smtpServer;
	private static String altSmtpServer;
	
	public MailUtil(String fromAddress, String smtpServer, String altSmtpServer){
		this.fromAddress= fromAddress;
		this.smtpServer= smtpServer;
		this.altSmtpServer= altSmtpServer;
	}
	
	/**
	  * @Description：发送邮件
	  * @param pm
	  * @param debug
	  */
	public void sendEmail(PromotionMail pm, boolean debug){
		try {
			sendEmail(fromAddress, pm.toAddress, pm.subject, pm.message, smtpServer, debug);
		} catch (Exception e1) {
			try {
				sendEmail(fromAddress, pm.toAddress, pm.subject, pm.message, altSmtpServer, debug);
			} catch (Exception e2) {
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
			}
		}
	}
	
	private void sendEmail(String from, String to, String subject, String message, String server, boolean debug){
//		假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(from).append("\n");
		buffer.append("To:").append(to).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());
	}
}
