package com.coderising.ood.srp;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

	private String smtpHost = null;
	private String altSmtpHost = null;
	private String fromAddress = null;
	
	static Map<String,String> configurations = new HashMap<>();
	static{
		configurations.put(ConfigurationKeys.SMTP_SERVER, "smtp.163.com");
		configurations.put(ConfigurationKeys.ALT_SMTP_SERVER, "smtp1.163.com");
		configurations.put(ConfigurationKeys.EMAIL_ADMIN, "admin@company.com");
	}
	/**
	 * 应该从配置文件读， 但是这里简化为直接从一个map 中去读
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		
		return configurations.get(key);
	}
	
	public Configuration(){
		setSMTPHost();
		setAltSMTPHost();
		setFromAddress();
	}
	
	
	private void setSMTPHost() 
	{
		smtpHost = ConfigurationKeys.SMTP_SERVER; 
	}
	
	private void setAltSMTPHost() 
	{
		altSmtpHost = ConfigurationKeys.ALT_SMTP_SERVER; 

	}

	private void setFromAddress()
	{
		fromAddress = ConfigurationKeys.EMAIL_ADMIN;
	}
	
	public String getFromAddress() 
	{
		return fromAddress;
	}
	
	public String getSMTPHost()
	{
		return smtpHost;
	}
	
	public String getAltSMTPHost()
	{
		return altSmtpHost;
	}

	

}
