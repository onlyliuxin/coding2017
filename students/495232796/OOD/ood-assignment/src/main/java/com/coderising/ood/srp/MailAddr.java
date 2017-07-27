package com.coderising.ood.srp;

public class MailAddr {
	protected String smtpHost = null;
	protected String altSmtpHost = null; 
	protected String fromAddress = null;
	protected String toAddress = null;
	
	public MailAddr(Configuration config) {
		reloadconfig(config);
	}
	
	public void reloadconfig(Configuration config) {
		smtpHost = config.getProperty(CommonKeys.SMTP_SERVER); 
		altSmtpHost = config.getProperty(CommonKeys.ALT_SMTP_SERVER); 
		fromAddress = config.getProperty(CommonKeys.EMAIL_ADMIN); 
	}
	
	public void setToAddress(String address) {
		toAddress = address;
	}
	
	public String getToAddress() {
		return toAddress;
	}
	
	public boolean checkToAddress() {
		return toAddress.length() > 0;
	}
	
	public String getSmtpHost() {
		return smtpHost;
	}

	public String getAltSmtpHost() {
		return altSmtpHost;
	}
	
	public String getFromAddress() {
		return fromAddress;
	}
}
