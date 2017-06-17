package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class Mail {
	
	private String smtpHost = null;
	private String altSmtpHost = null; 
	private String fromAddress = null;
	private String toAddress = null;
	protected String subject = null;
	protected String message = null;
	protected String sendMailQuery = null;

	
	public Mail(File file, Boolean mailDebug) throws Exception
	{
		readFile(file);	
		setSMTPHost();
		setAltSMTPHost(); 
		setFromAddress();
							
		setSendMailQuery();			
		List mailingList = loadMailingList();					
		sendEMails(mailDebug, mailingList); 
		
	}
	protected abstract void readFile(File fie) throws IOException;	
		
	protected void setSMTPHost() 
	{
		smtpHost = Configuration.getProperty(ConfigurationKeys.SMTP_SERVER); 
	}
	
	
	protected void setAltSMTPHost() 
	{
		altSmtpHost = Configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER); 
	}
	
	
	protected void setFromAddress() 
	{
		fromAddress = Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN); 
	}
	
	protected abstract void setSendMailQuery() throws Exception;
	
			
	protected List loadMailingList() throws Exception {			//user information, name and email address
		return DBUtil.query(this.sendMailQuery);
	}
		
		
	protected abstract void setMessage(String name) throws IOException;
	
	
			
	
	
	protected void setToAddress(HashMap userInfo){
		toAddress = (String) userInfo.get(Configuration.EMAIL_KEY); 		
	}
	
	protected void configureEMail(HashMap userInfo) throws IOException 
	{
		if (toAddress.length() > 0) 
			setMessage((String)userInfo.get(Configuration.NAME_KEY)); 
	}
	
	
	protected void sendEMails(boolean debug, List mailingList) throws IOException 
	{

		System.out.println("开始发送邮件");
	

		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				HashMap userInfo = (HashMap) iter.next();				
				setToAddress(userInfo);				
				configureEMail(userInfo);  
				try 
				{
					if (toAddress.length() > 0)
						sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);
				} 
				catch (Exception e) 
				{
					
					try {
						sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug); 
						
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
	
	public void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
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
