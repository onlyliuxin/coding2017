package com.coderising.ood.srp_restructure_1.pojo;

public class MailServiceConfiguration {

	private String SMTPHost;
	private String AltSMTPHost;
	private String FromAddress;

	public String getSMTPHost() {
		return SMTPHost;
	}

	public MailServiceConfiguration setSMTPHost(String sMTPHost) {
		SMTPHost = sMTPHost;
		return this;
	}

	public String getAltSMTPHost() {
		return AltSMTPHost;
	}

	public MailServiceConfiguration setAltSMTPHost(String altSMTPHost) {
		AltSMTPHost = altSMTPHost;
		return this;
	}

	public String getFromAddress() {
		return FromAddress;
	}

	public MailServiceConfiguration setFromAddress(String fromAddress) {
		FromAddress = fromAddress;
		return this;
	}
}
