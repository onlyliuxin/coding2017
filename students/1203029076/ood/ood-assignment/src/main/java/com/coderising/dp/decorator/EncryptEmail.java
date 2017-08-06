package com.coderising.dp.decorator;

public class EncryptEmail extends EmailDecorator {
	public EncryptEmail(Email email) {
		// TODO Auto-generated constructor stub
		super(email);
	}
	
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return encrypt(super.getContent());
	}
	
	private String encrypt(String content) {
		// concrete encrypt algorithm
		return content+"encrypt";
	}
}
