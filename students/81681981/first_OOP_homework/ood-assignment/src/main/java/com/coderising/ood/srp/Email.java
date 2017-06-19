package com.coderising.ood.srp;

import java.util.HashMap;
import java.util.Map;
/*
 * email
 */

public class Email {
	
	static Map<String,String> configurations = new HashMap<>();
	static{
		configurations.put(ConfigurationKeys.SMTP_SERVER, "smtp.163.com");
		configurations.put(ConfigurationKeys.ALT_SMTP_SERVER, "smtp1.163.com");
		configurations.put(ConfigurationKeys.EMAIL_ADMIN, "admin@company.com");
	}
	protected String smtpHost = null;
	protected String altSmtpHost = null; 
	protected String fromAddress = null;
	protected String subject = null;
	protected String message = null;
	
	/**
	 * 应该从配置文件读， 但是这里简化为直接从一个map 中去读
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		
		return configurations.get(key);
	}
	
	public String getSmtpHost() {
		return smtpHost;
	}


	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}


	public String getAltSmtpHost() {
		return altSmtpHost;
	}


	public void setAltSmtpHost(String altSmtpHost) {
		this.altSmtpHost = altSmtpHost;
	}


	public String getFromAddress() {
		return fromAddress;
	}


	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	protected void setSMTPHost() 
	{
		smtpHost = this.getProperty(ConfigurationKeys.SMTP_SERVER); 
	}

	
	protected void setAltSMTPHost() 
	{
		altSmtpHost = this.getProperty(ConfigurationKeys.ALT_SMTP_SERVER); 

	}

	
	protected void setFromAddress() 
	{
			fromAddress = this.getProperty(ConfigurationKeys.EMAIL_ADMIN); 
	}

}
