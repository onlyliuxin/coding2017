package com.coderising.ood.srp;

import java.util.List;

public class MailUtil {
	
	public static void sendEmail(List<Product> products, List<User> users){
		
		for(Product p : products){
			for(User u : users){
				sendEmailToUser(p, u);
			}
		}
		
	}

	private static void sendEmailToUser(Product product, User user) {

		String message = "尊敬的 "+user.getName()+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;
		
		String fromAddress = Configuration.EMAIL_ADMIN;
		String subject = "您关注的产品降价了";
		String smtpHost = Configuration.SMTP_SERVER;
		String altSmtpHost = Configuration.ALT_SMTP_SERVER;
		
		try{
			_sendEmailReal(user.getEmail(), fromAddress, subject, message, smtpHost, false);
		}catch(Exception e){
			_sendEmailReal(user.getEmail(), fromAddress, subject, message, altSmtpHost, false);
		}
		
	}
	
	private static void _sendEmailReal(String toAddress, String fromAddress, String subject, String message, String smtpHost,
			boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());
		
	}
	
}
