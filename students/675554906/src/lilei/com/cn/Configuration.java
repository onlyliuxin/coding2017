package lilei.com.cn;
import java.util.HashMap;
import java.util.Map;
/**
 * Configuration 读取/获取配置信息类
 * 唯一能引起此类变化的是配置文件的改变
 * 
 * */
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

}
