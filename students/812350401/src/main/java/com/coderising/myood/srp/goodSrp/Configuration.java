package com.coderising.myood.srp.goodSrp;

import com.coderising.myood.srp.ConfigurationKeys;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

	static Map<String,String> configurations = new HashMap<>();
	static{
		configurations.put(com.coderising.myood.srp.ConfigurationKeys.SMTP_SERVER, "smtp.163.com");
		configurations.put(com.coderising.myood.srp.ConfigurationKeys.ALT_SMTP_SERVER, "smtp1.163.com");
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

}
