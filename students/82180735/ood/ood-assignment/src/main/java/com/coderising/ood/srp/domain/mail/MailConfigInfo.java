package com.coderising.ood.srp.domain.mail;

/**
 * Created by justin on 17/6/12.
 */
public class MailConfigInfo {
	private String smtpHost;
	private String altSmtpHost;
	private String fromAddress;

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
