package com.coderising.ood.srp.config;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置类，模拟从配置文件中读取参数
 * <p>标题: </p>
 * <p>描述: </p>
 * @autho zx
 * @time 2017年6月12日 下午11:44:36
*/
public class Configuration {

	private static Map<String,String> configurations = new HashMap<String,String>();
	
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

}
