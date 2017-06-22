package test.java.org.coderising.liteaop;


import java.util.HashMap;
import java.util.Map;


public class Configuration {

	
	protected String smtpHost = null;
	protected String altSmtpHost = null; 
	protected String fromAddress = null;
	static Map<String,String> configurations = new HashMap();
	
	
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
	
	protected String setSMTPHost() 
	{
		smtpHost =  getProperty(ConfigurationKeys.SMTP_SERVER); 
		return smtpHost;
	}

	
	protected String setAltSMTPHost() 
	{
		altSmtpHost = getProperty(ConfigurationKeys.ALT_SMTP_SERVER); 
		return altSmtpHost;
	}

	
	protected String setFromAddress() 
	{
			fromAddress = getProperty(ConfigurationKeys.EMAIL_ADMIN); 
			return fromAddress;
	}



}
 