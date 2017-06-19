package com.coderising.ood.srp;

import java.io.IOException;


public class MailUtil {
	protected String smtpHost = null;
	protected String altSmtpHost = null;
	public MailUtil(String smtpHost, String altSmtpHost) {
		this.smtpHost = smtpHost;
		this.altSmtpHost = altSmtpHost;
	}
	private static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
								 boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());

	}
	public  void sendPromotionMail(boolean debug, PromotionMail mail) throws IOException {
		System.out.println("开始发送邮件");
		String toAddress;
		String message;
		if (mail.toAddressList != null) {
			while (mail.hasNextToAddress()) {
				toAddress = mail.getNextToAddress();
				if (toAddress.length() > 0){
					message = mail.generateMessageToCurrentToAddress();
					try {
							MailUtil.sendEmail(toAddress, mail.fromAddress, mail.subject, message, this.smtpHost, debug);
					}
					catch (Exception e) {
						try {
							MailUtil.sendEmail(toAddress, mail.fromAddress, mail.subject, message, this.altSmtpHost, debug);

						} catch (Exception e2) {
							System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
						}
					}
				}else {
					System.out.println("目标地址为空");
				}
			}
		} else {
			System.out.println("没有邮件发送");
		}
	}
}
