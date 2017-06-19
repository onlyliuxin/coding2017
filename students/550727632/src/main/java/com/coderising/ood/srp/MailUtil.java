package com.coderising.ood.srp;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class MailUtil {

	private static String fromAddress;
	private static String smtpHost;
	private static String altSmtpHost;

	static {
		Configuration config = new Configuration();
		fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
	}

	public static void sendPromotionEmail(PromotionMail mail, Product product, boolean debug) {
		System.out.println("开始发送邮件");
		List<User> userList = DBUtil.loadProductUsers(product);
		if(userList != null && !userList.isEmpty()){
			for (User user : userList) {
				mail.setMessage(user, product);
				if (StringUtils.isNotEmpty(user.getEmail())){
					sendEmail(mail.getSubject(), mail.getMessage(), user.getEmail(), debug);
				}
			}
		} else {
			System.out.println("没有需要发送的邮件");
		}
	}
	
	public static void sendEmail(String subject, String message,String toAddress,boolean debug) {
		// 假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		// 先用smtpHost发送
		// 如果失败用altSmtpHost发送
		// 如果还失败，则记录日志
		try {
			buffer.append("smtpHost:").append(smtpHost).append("\n");
		} catch (Exception e) {
			try {
				buffer.append("smtpHost:").append(altSmtpHost).append("\n");
			} catch (Exception e2) {
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
			}
		}
		System.out.println(buffer.toString());
	}
}
