package com.coderising.dp.decorator;

public class EmailEncryptionDecorator extends EmailDecorator{

	public EmailEncryptionDecorator(Email e) {
		super(e);
	}
	
	public String encryptContext() {
		return this.email.getContent();
	}
	
	public String decryptContext() {
		return this.email.getContent();
	}

	public String getContent() {
		return this.encryptContext();
	}
}
