package com.coderising.ood.srp.config;

/**
 * 邮箱连接配置类
 *
 */
public class ConnectionConfig {
	private String smtpHost;
	private String altSmtpHost;
	private String fromAddress;

	public ConnectionConfig(Configuration config) {
		this.smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER);
		this.altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
		this.fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getAltSmtpHost() {
		return altSmtpHost;
	}

	public void setAltSmtpHost(String altSmtpHost) {
		this.altSmtpHost = altSmtpHost;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
}
