package com.coderising.ood.srp_restructure_1.pojo;

public class Mail {

	private String subject;
	private String message;
	private String toAddress;

	public Mail(String subject, String message, String toAddress) {
		this.subject = subject;
		this.message = message;
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

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
}
