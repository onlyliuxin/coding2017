package com.coderising.dp.decorator;

public class EmailEcript extends EmailDecorator {

	public EmailEcript(Email email) {
		super(email);
	}

	@Override
	public String getContent() {
		return "**" + email.getContent() + "**";
	}

}
