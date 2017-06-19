package com.coderising.ood.srp;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

	public static final String EMAIL_ADMIN = "email.admin";
	private Map<String,String> configurations = new HashMap();
	private static Configuration configuration = new Configuration();
	{
		configurations.put(EMAIL_ADMIN, "admin@company.com");
	}
	/**
	 * 应该从配置文件读， 但是这里简化为直接从一个map 中去读
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return configurations.get(key);
	}

	public static Configuration getInstance(){
		return configuration;
	}
}
