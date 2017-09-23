package com.coderising.ood.srp;

import java.util.List;

public class Mail {
	private User user;
	private String subject;
	private String message;

	public Mail(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public String getMessage() {
		return "??? " + user.getName() + ", ????????? " + buildDesc() + " ??????????????!";
	}

	public String getSubject() {
		return "???????????????";
	}

	private String buildDesc() {
		List<Product> products=user.getProducts();
		StringBuffer sb = new StringBuffer();
		for (Product p : products) {
			sb.append(p.getProductDesc());
		}
		return sb.toString();
	}
}
