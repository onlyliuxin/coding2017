package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class Mail {
	
	protected String smtpHost = null;
	protected String altSmtpHost = null; 
	protected String fromAddress = null;
	protected String toAddress = null;
	protected String subject = null;
	protected String message = null;
	protected String sendMailQuery = null;

	
	public Mail(File file) throws Exception
	{
		readFile(file);	
		setSMTPHost();
		setAltSMTPHost(); 
		setFromAddress();						
		 
		
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
	
	
	
			
	protected List loadMailingList() throws Exception {			//user information, name and email address
		return DBUtil.query(this.sendMailQuery);
	}
		
		
	//protected abstract void setMessage(String name) throws IOException;			
	
	
	protected void setToAddress(HashMap userInfo){
		toAddress = (String) userInfo.get(Configuration.EMAIL_KEY); 		
	}
	
	
	protected abstract void emailProcessing(List mailingList, boolean debug) throws IOException, Exception;
	
	
	protected void sendEMails(boolean debug) throws Exception 
	{
		
		List mailingList = loadMailingList();	//persons
		
		System.out.println("开始发送邮件");
		emailProcessing(mailingList, debug);

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
