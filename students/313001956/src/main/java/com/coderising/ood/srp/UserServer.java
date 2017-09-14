package com.coderising.ood.srp;

import java.util.List;

public class UserServer {
	public List<User> getUserByProduct(Product product) {
		String sendMailQuery = "Select name from subscriptions " + "where product_id= '" + product.getProductID() + "' "
				+ "and send_mail=1 ";
		System.out.println("load Query set");
		List<User> userList = DBUtil.query(sendMailQuery, product);
		return userList;
	}
}
