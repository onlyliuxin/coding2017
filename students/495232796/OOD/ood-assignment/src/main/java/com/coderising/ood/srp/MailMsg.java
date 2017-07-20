package com.coderising.ood.srp;

public class MailMsg {
	protected String subject = null;
	protected String message = null;
	
	public MailMsg(String sub, String msg) {
		this.subject = sub;
		this.message = msg;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getMessage() {
		return message;
	}
}
