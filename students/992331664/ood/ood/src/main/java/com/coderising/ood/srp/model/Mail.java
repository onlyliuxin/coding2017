package com.coderising.ood.srp.model;

public class Mail {
	private String subject;
	private String message;
	private String toAddress;

	public Mail() {
		super();
	}

	public Mail(String subject, String message, String toAddress) {
		super();
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

	@Override
	public String toString() {
		return "Mail [subject=" + subject + ", message=" + message + ", toAddress=" + toAddress + "]";
	}

}
