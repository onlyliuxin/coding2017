package com.coderising.ood.srp;

public class MailUtil {

	public static void sendEmail(String toAddress, String fromAddress, String subject, String message, MailHosts smtpHosts) {
		for(String hosts : smtpHosts.getHosts()) {
			try {
				//假装发了一封邮件
				StringBuilder buffer = new StringBuilder();
				buffer.append("From:").append(fromAddress).append("\n");
				buffer.append("To:").append(toAddress).append("\n");
				buffer.append("Subject:").append(subject).append("\n");
				buffer.append("Content:").append(message).append("\n");
				System.out.println(buffer.toString());
				break;
			} catch (Exception e) {
				System.err.println(String.format("通过SMTP服务器(%s)发送邮件失败: %s", hosts,e.getMessage()));
			}
		}
	}
}
