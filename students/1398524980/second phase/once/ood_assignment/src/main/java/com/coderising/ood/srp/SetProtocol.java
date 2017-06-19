package main.java.com.coderising.ood.srp;

public class SetProtocol {

	private String smtpHost = null;
	private String altSmtpHost = null;

	private Configuration config;

	protected void setProtocol() {
		config = Configuration.getConfig();
		setSMTPHost();
		setAltSMTPHost();
	}

	private void setSMTPHost() {
		smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
	}

	private void setAltSMTPHost() {
		altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
	}

	protected String getSMTPHost() {
		return smtpHost;
	}

	protected String getAltSMTPHost() {
		return altSmtpHost;
	}

}
