package com.coderising.ood.srp;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

	private static Map<String,String> configurations = new HashMap<>();
	private static String smtpHost;
	private static String altSmtpHost;
	private static String fromAddress;
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
	public Configuration(){
		setSMTPHost();
		setAltSMTPHost();
		setFromAddress();
	}
	public String getProperty(String key) {
		
		return configurations.get(key);
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
	
	public static String getSmtpHost() {
		return smtpHost;
	}
	public static String getAltSmtpHost() {
		return altSmtpHost;
	}
	public static String getFromAddress() {
		return fromAddress;
	}

	
}
