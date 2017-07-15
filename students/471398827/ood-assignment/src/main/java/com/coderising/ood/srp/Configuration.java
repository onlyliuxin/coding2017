package com.coderising.ood.srp;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

	static Map<String, String> configurations = new HashMap<>();
	static boolean MAIL_DEBUG = false;
	static{
		configurations.put(ConfigurationKeys.SMTP_SERVER, "smtp.163.com");
		configurations.put(ConfigurationKeys.ALT_SMTP_SERVER, "smtp1.163.com");
		configurations.put(ConfigurationKeys.EMAIL_ADMIN, "admin@company.com");
		configurations.put(ConfigurationKeys.FILE_PATH, "/Users/szf/git/coding2017/students/471398827/ood-assignment/src/main/java/com/coderising/ood/srp/product_promotion.txt");
	}
	/**
	 * 应该从配置文件读， 但是这里简化为直接从一个map 中去读
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		
		return configurations.get(key);

	}

}
