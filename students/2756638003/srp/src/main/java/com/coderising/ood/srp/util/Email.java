package com.coderising.ood.srp.util;

/**
 * 邮件类
 */
public class Email {

	private String toAddress;
	private String fromAddress;
	private String subject;
	private String message;
	private String smtpHost;

	public Email(String toAddress, String fromAddress, String subject,
			String message, String smtpHost) {
		super();
		this.toAddress = toAddress;
		this.fromAddress = fromAddress;
		this.subject = subject;
		this.message = message;
		this.smtpHost = smtpHost;
	}

	public Email() {
		super();
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	@Override
	public String toString() {
		return "Email [toAddress=" + toAddress + ", fromAddress=" + fromAddress
				+ ", subject=" + subject + ", message=" + message
				+ ", smtpHost=" + smtpHost + "]";
	}

}
