package com.coderising.dp.decorator;

public abstract class EmailDecorator implements Email {
	protected volatile Email email;

	public EmailDecorator(Email email) {
		this.email = email;
	}

	public String getContent() {
		return email.getContent();
	}
}
