package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MailUtil {
	
	private static Configuration config=new Configuration(); ;
	private static ProductUtil productUtil = new ProductUtil();
	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";

	protected static void sendEmail(String userName,String toAddress,String smtpHost,Boolean debug) throws IOException 
	{
		String subject = "您关注的产品降价了";
		String message = "尊敬的 "+userName+", 您关注的产品 " + productUtil.getProductDesc() + " 降价了，欢迎购买!" ;
		String fromAddress=config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer);
	}
	


	private static void sendEmail(String userName,String toAddress,Boolean debug) {
		try 
		{
			String smtpHost=config.getProperty(ConfigurationKeys.SMTP_SERVER);
			sendEmail( userName,toAddress,smtpHost, debug);
		} 
		catch (Exception e) 
		{
			
			try {
				String altSmtpHost=config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
				sendEmail( userName,toAddress,altSmtpHost, debug);
				
			} catch (Exception e2) 
			{
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
			}
		}
	}

	protected static void  sendEMails(boolean debug,List mailingList) throws IOException 
	{

		System.out.println("开始发送邮件");
	
       
		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				HashMap<String, String> userInfo = (HashMap<String, String>)iter.next();
				String userName = userInfo.get(NAME_KEY);
				String toAddress = userInfo.get(EMAIL_KEY);
				sendEmail( userName, toAddress, debug);
			}	
		}

		else {
			System.out.println("没有邮件发送");	
		}

	}

	
}
