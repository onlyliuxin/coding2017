package com.coderising.dp.decorator;

public abstract class EmailDecorator implements Email{
	protected Email email;
	
	public EmailDecorator(Email e) {
		this.email = e;
	}
	
	public String getContent(){
		return this.email.getContent();
	}
}