package com.github.orajavac.coding2017.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MailUtil {
	
	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	
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
	
	public static String setSMTPHost(Configuration config) 
	{
		return config.getProperty(ConfigurationKeys.SMTP_SERVER); 
	}

	
	public static String setAltSMTPHost(Configuration config) 
	{
		return config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER); 

	}

	
	public static String setFromAddress(Configuration config) 
	{
		return config.getProperty(ConfigurationKeys.EMAIL_ADMIN); 
	}
	
	public static void setMessage(HashMap userInfo,Product p,Mail mail) throws IOException 
	{
		
		String name = (String) userInfo.get(NAME_KEY);
		
		mail.setSubject("您关注的产品降价了");
		
		String message = "尊敬的 "+name+", 您关注的产品 " + p.getProductDesc() + " 降价了，欢迎购买!" ;		
		mail.setMessage(message);		
		

	}
	
	public static void sendEMails(boolean debug, List mailingList,Product p,Mail mail) throws IOException 
	{

		System.out.println("开始发送邮件");
		

		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				HashMap userInfo = (HashMap) iter.next();
				String toAddress = (String) userInfo.get(EMAIL_KEY); 
				if (toAddress.length() > 0) 
					setMessage(userInfo,p,mail);
				try 
				{
					if (toAddress.length() > 0)
						MailUtil.sendEmail(toAddress, mail.getFromAddress(), mail.getSubject(), mail.getMessage(), mail.getSmtpHost(), debug);
				} 
				catch (Exception e) 
				{
					
					try {
						MailUtil.sendEmail(toAddress, mail.getFromAddress(), mail.getSubject(), mail.getMessage(), mail.getAltSmtpHost(), debug); 
						
					} catch (Exception e2) 
					{
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
					}
				}
			}
			

		}

		else {
			System.out.println("没有邮件发送");
			
		}

	}
	
}
