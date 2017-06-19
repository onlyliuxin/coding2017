package main.java.com.coderising.ood.srp;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

	private static Configuration config;

	static Map<String, String> configurations = new HashMap<>();
	static {
		configurations.put(ConfigurationKeys.SMTP_SERVER, "smtp.163.com");
		configurations.put(ConfigurationKeys.ALT_SMTP_SERVER, "smtp1.163.com");
		configurations.put(ConfigurationKeys.EMAIL_ADMIN, "admin@company.com");
	}

	private Configuration() {
	}

	protected static Configuration getConfig() {
		return config;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {

		return configurations.get(key);
	}

}
