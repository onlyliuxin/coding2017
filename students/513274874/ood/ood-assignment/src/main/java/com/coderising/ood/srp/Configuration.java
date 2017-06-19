package com.coderising.ood.srp;

import com.coderising.ood.srp.constants.ConfigurationKeys;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

	protected String smtpHost = null;
	protected String altSmtpHost = null;
	protected String fromAddress = null;

	private static Configuration configuration = null;
	static Map<String,String> configurations = new HashMap<String,String>();
	static{
		configurations.put(ConfigurationKeys.SMTP_SERVER, "smtp.163.com");
		configurations.put(ConfigurationKeys.ALT_SMTP_SERVER, "smtp1.163.com");
		configurations.put(ConfigurationKeys.EMAIL_ADMIN, "admin@company.com");
	}


	public static Configuration getInstance(){

		if(configuration == null) {
			return new Configuration();
		}
		return configuration;
	}

	/**
	 * 应该从配置文件读， 但是这里简化为直接从一个map 中去读
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		
		return configurations.get(key);
	}

	private Configuration() {
		setSMTPHost();
		setAltSMTPHost();
		setFromAddress();
	}

	protected void setSMTPHost()
	{
		smtpHost = getProperty(ConfigurationKeys.SMTP_SERVER);
	}


	protected void setAltSMTPHost()
	{
		altSmtpHost = getProperty(ConfigurationKeys.ALT_SMTP_SERVER);

	}

	protected void setFromAddress() {
		fromAddress = getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public String getAltSmtpHost() {
		return altSmtpHost;
	}

	public String getFromAddress() {
		return fromAddress;
	}
}
