package com.coderising.ood.srp;

import java.io.File;
import java.util.HashMap;

public class MailUtil {

	private MailContent mc = null;
	private String toAddress = null;
	private String fromAddress = null;
	private String subject = null;
	private String message = null;
	private String smtpHost = null;
	private String altSmtpHost = null;
	
	public MailUtil(Configuration config, File f, HashMap userInfo, String prodInfo){
		mc = new MailContent(f, userInfo, prodInfo);
		toAddress = mc.getToAddress();
		subject = mc.getSubject();
		message = mc.getMessage();
		fromAddress = config.getFromAddress();
		smtpHost = config.getSMTPHost();
		altSmtpHost = config.getAltSMTPHost();
	}
	public void sendEmail(boolean debug) {
		try 
		{
			if (toAddress.length() > 0)
				sendEmail(smtpHost);
		} 
		catch (Exception e) 
		{
			try {
				sendEmail(altSmtpHost); 
				
			} catch (Exception e2) 
			{
				System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage()); 
			}
		}
	}

	private void sendEmail(String server){
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAddress).append("\n");
		buffer.append("To:").append(toAddress).append("\n");
		buffer.append("Subject:").append(subject).append("\n");
		buffer.append("Content:").append(message).append("\n");
		System.out.println(buffer.toString());		
	}
}
