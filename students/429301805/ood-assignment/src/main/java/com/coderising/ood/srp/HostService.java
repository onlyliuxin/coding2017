package com.coderising.ood.srp;

public class HostService {
	
	private static String smtpHost; 
	
	private static String altSmtpHost;
	
	public static void setSMTPHost(Configuration config) 
	{
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER); 
	}

	
	public static void setAltSMTPHost(Configuration config) 
	{
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER); 

	}


	public static String getSmtpHost() {
		return smtpHost;
	}


	public static String getAltSmtpHost() {
		return altSmtpHost;
	}
	
	

}
