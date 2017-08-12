package com.coderising.ood.srp;

import java.util.List;

public class Mail {
    private User user;

    public Mail(User user) {
	this.user = user;
    }

    public String getAddress() {
	return user.getEMailAddress();
    }

    public String getSubjcet() {
	return "您关注的商品降价了！";
    }

    public String getBody() {
	return "尊敬的用户: " + user.getName() + ", 您关注的商品： " + this.buildProductDescList();
    }

    private String buildProductDescList() {
	List<Product> products = user.getSubscribedProducts();

	return null;
    }

    public String getSubject() {

	return null;
    }
}
