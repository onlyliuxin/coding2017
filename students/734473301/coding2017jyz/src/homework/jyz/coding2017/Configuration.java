package homework.jyz.coding2017;
import java.util.HashMap;
import java.util.Map;

/**
 *  主机配置类
 */
public class Configuration {

	public static final String SMTP_SERVER = "smtp.server";
	public static final String ALT_SMTP_SERVER = "alt.smtp.server";
	public static final String EMAIL_ADMIN = "email.admin";

	private  Map<String,String> configurations = new HashMap<>();

	public Configuration() {
		configurations.put(SMTP_SERVER, "smtp.163.com");
		configurations.put(ALT_SMTP_SERVER, "smtp1.163.com");
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

}
