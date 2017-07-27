package com.coderising.myood.srp.goodSrp;

import com.coderising.myood.srp.goodSrp.template.MailBodyTemplate;
import com.coderising.myood.srp.goodSrp.template.TextMailBodyTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class Mail {

	private User user;
	MailBodyTemplate mailBodyTemplate;
	public Mail(User u){
		this.user = u;	
	}
	public String getAddress(){
		return user.getEMailAddress();
	}
	public String getSubject(){
		return "您关注的产品降价了";
	}
	public String getBody(){
		mailBodyTemplate = new TextMailBodyTemplate(user.getName(), buildProductDescList(), getAddress());
		return mailBodyTemplate.render();
	}
	private String buildProductDescList() {
		List<Product> products = user.getSubscribedProducts();
		//.... 实现略...
		return products.stream().map(Object::toString)
				.collect(Collectors.joining(", "));
	}
}
