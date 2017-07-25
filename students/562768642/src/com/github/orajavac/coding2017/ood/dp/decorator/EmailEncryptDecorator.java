package com.github.orajavac.coding2017.ood.dp.decorator;

public class EmailEncryptDecorator implements Email{
	private Email email;

	public EmailEncryptDecorator(Email e){
		this.email = e;
	}
	
	public String getContent(){
		return "$a^@<c^f(!";	
	}
}
