package com.coderising.dp.decorator;

public class EmailProxy implements Email{
	Email email;
	
	@Override
	public String getContent() {
		String content=email.getContent()+"\n本邮件仅为个人观点，并不代表公司立场";
		System.out.println(content);
		content=Encrypt.SHA256(content);
		System.out.println(content);
		return content;
	}
	
	public void setEmail(Email email) {
		this.email = email;
	}

	public static void main(String[] args) {
		EmailImpl email=new EmailImpl("hello world!");
		EmailProxy proxy=new EmailProxy();
		EmailProxy proxy1=new EmailProxy();
		proxy.setEmail(email);
	//	proxy.getContent();
		proxy1.setEmail(proxy);
		proxy1.getContent();
	}

}
