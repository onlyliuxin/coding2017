package com.coderising.ood.srp.goodSrp.template;

import com.coderising.ood.srp.goodSrp.Mail;


public class TextMailBodyTemplate implements MailBodyTemplate {
	private Mail mail;
	String fromAdress;
	public TextMailBodyTemplate(Mail mail, String fromAdress){
		this.mail = mail;
		this.fromAdress = fromAdress;
	}
	
	@Override
	public String render() {
		//使用某种模板技术实现Render
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(fromAdress).append("\n");
		buffer.append("To:").append(mail.getAddress()).append("\n");
		buffer.append("Subject:").append(mail.getSubject()).append("\n");
		buffer.append("Content:").append(mail.getBody()).append("\n");
		return buffer.toString();
	}

}
