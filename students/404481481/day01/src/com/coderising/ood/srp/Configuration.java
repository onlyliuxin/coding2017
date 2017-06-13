package com.coderising.ood.srp;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

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

	
	public static void init(PromotionMail promotion){
		Configuration config = new Configuration();
		promotion.smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
		promotion.altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
		promotion.fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}
}
