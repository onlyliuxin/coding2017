package com.coderising.dp.decorator;

public class SendOutEmail extends EmailDecorator{
	public SendOutEmail(Email email) {
		// TODO Auto-generated constructor stub
		super(email);
	}
	
	@Override
	public String getContent() {
		// TODO Auto-generated method stub
		return super.getContent()+"\n本邮件仅为个人观点，并不代表公司立场";
	}
}
