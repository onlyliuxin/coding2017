package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MailUtil {

	private static String smtpHost = null;
	private static String altSmtpHost = null; 
	private static String fromAddress = null;
	private static String toAddress = null;
	private static String subject = null;
	private static String message = null;
	
	static {
		Configuration config = new Configuration();
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER); 
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER); 
		fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}
	
	protected static void sendEmail(String toAddress, String subject, String message,
			boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());
		
	}
	
	protected static void sendEMails(boolean debug,Product product, List mailingList) throws IOException {

		System.out.println("开始发送邮件");

		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				configureEMail((HashMap) iter.next(),product);  
				try 
				{
					if ( toAddress.length() > 0 )
						sendEmail(toAddress,  subject,  message,  debug);
				} 
				catch (Exception e) 
				{
					
					try {
						sendEmail(toAddress, subject, message, debug); 
						
					} catch (Exception e2) 
					{
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
					}
				}
			}
		} else {
			System.out.println("没有邮件发送");
			
		}

	}
	
	private static void setMessage( HashMap userInfo,Product product) throws IOException {
		String name = (String) userInfo.get( ConfigurationKeys.NAME_KEY );
		subject = "您关注的产品降价了";
		message = "尊敬的 "+name+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;		
	}

	private static void configureEMail(HashMap userInfo,Product product) throws IOException 
	{
		toAddress = (String) userInfo.get( ConfigurationKeys.EMAIL_KEY ); 
		if (toAddress.length() > 0) 
			setMessage( userInfo, product ); 
	}

	
}
