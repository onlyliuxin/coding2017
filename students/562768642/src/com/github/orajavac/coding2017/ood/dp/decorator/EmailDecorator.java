package com.github.orajavac.coding2017.ood.dp.decorator;

public class EmailDecorator implements Email{
	
	private Email email;
	
	public EmailDecorator(Email e){
		this.email = e;
	}
	
	public String getContent(){
		return email.getContent()+",本邮件仅为个人观点，并不代表公司立场";	
	}
}
