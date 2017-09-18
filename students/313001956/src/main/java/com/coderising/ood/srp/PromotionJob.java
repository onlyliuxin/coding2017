package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionJob {
	private ProductServer productServer;
	private UserServer userServer;

	public PromotionJob(ProductServer productServer, UserServer userServer) {
		this.productServer = productServer;
		this.userServer = userServer;
	}

	public void run() throws IOException {
		List<Product> productList = productServer.getProductList();
		Product product = productList.get(0);
		System.out.println("???ID = " + product.getProductID() + "\n");
		System.out.println("??????? = " + product.getProductDesc() + "\n");

		List<User> users = userServer.getUserByProduct(product);

		MailSender mailSender = new MailSender(new Configuration());
		for (User user : users) {			
			mailSender.sendEmails(new Mail(user));
		}
	}

}
