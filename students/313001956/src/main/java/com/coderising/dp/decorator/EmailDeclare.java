package com.coderising.dp.decorator;

import org.junit.experimental.theories.Theories;

public class EmailDeclare extends EmailDecorator {

	
	public EmailDeclare(Email email) {
		super(email);
	}

	@Override
	public String getContent() {
	return	email.getContent()
			+"\r\n"
			+"???????????????????????????";
	}

}
