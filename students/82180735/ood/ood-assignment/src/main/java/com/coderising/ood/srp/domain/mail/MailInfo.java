package com.coderising.ood.srp.domain.mail;

/**
 * Created by justin on 17/6/12.
 */
public class MailInfo {
	private String toAddress;
	private String subject;
	private String message;


	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
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
}
