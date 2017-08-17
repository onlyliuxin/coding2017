package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;

public class ConfigureEmail {

	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";

	private Configuration config = null;
	private EmailBean email = new EmailBean();
	private Product product;

	private String message;
	private String subject;
	private String toAddress;

	public ConfigureEmail(Configuration config, Product product,
			HashMap userInfo) {

		this.config = config;
		this.product = product;

		setMessage(userInfo);
		setToAddress(userInfo);

		setBean();

	}

	private void setBean() {

		email.setFromAddress(config.getFromAddress());
		email.setMessage(message);
		email.setSmtpHost(config.getSmtpHost());
		email.setSubject(subject);
		email.setToAddress(toAddress);
	}

	public EmailBean getEmail() {
		return email;
	}

	public void setMessage(HashMap userInfo) {

		String name = (String) userInfo.get(NAME_KEY);

		subject = "您关注的产品降价了";
		message = "尊敬的 " + name + ", 您关注的产品 " + product.getProductDesc()
				+ " 降价了，欢迎购买!";
	}

	protected void setToAddress(HashMap userInfo) {
		toAddress = (String) userInfo.get(EMAIL_KEY);
	}

}
