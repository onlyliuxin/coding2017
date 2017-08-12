package com.coderising.dp.decorator;

public abstract class EmailDecorator implements Email{
	protected Email email;
	
	public EmailDecorator(Email email) {
		// TODO Auto-generated constructor stub
		this.email = email;
	}
	
	public String getContent() {
		// TODO Auto-generated method stub
		return email.getContent();
	}
}