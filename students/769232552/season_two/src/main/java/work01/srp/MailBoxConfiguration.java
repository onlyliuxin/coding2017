package work01.srp;
import java.util.HashMap;
import java.util.Map;

public class MailBoxConfiguration {

	static Map<String,String> configurations = new HashMap<String, String>();
	static{
		configurations.put(ConfigurationKeys.SMTP_SERVER, "smtp.163.com");
		configurations.put(ConfigurationKeys.ALT_SMTP_SERVER, "smtp1.163.com");
		configurations.put(ConfigurationKeys.EMAIL_ADMIN, "admin@company.com");

	}

	private MailBox mailBox;

	public MailBoxConfiguration(){}

	public void config(){
		setSMTPHost();
		setAltSMTPHost();
		setFromAddress();
	}

	public void setMailBox(MailBox mailBox) {
		this.mailBox = mailBox;
	}

	private void setSMTPHost() {
		this.mailBox.setSmtpHost(getProperty(ConfigurationKeys.SMTP_SERVER));
	}


	private void setAltSMTPHost() {
		this.mailBox.setAltSmtpHost(getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
	}


	private void setFromAddress() {
		this.mailBox.setFromAddress(getProperty(ConfigurationKeys.EMAIL_ADMIN));
	}



	/**
	 * 应该从配置文件读， 但是这里简化为直接从一个map 中去读
	 * @param key
	 * @return
	 */
	private String getProperty(String key) {
		
		return configurations.get(key);
	}

	public class ConfigurationKeys {

		public static final String SMTP_SERVER = "smtp.server";
		public static final String ALT_SMTP_SERVER = "alt.smtp.server";
		public static final String EMAIL_ADMIN = "email.admin";

	}
}
