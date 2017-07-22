package com.coderising.ood.srp;

import java.util.List;

public class PromotionJob {

    private ProductService productService = null; // 获取production service
    private UserService userService = null;// 获取UserService

    public void run() {

	Configuration cfg = new Configuration();

	Product p = productService.getPromotionProduct();

	List<User> users = userService.getUsers(p);   //一次只读取了一件商品

	MailSender mailSender = new MailSender(cfg);

	for (User user : users) {
	    mailSender.sendMail(new Mail(user));
	}
    }
}
