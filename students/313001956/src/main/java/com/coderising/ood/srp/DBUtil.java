package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {

	/**
	 * ???????????? ????????????¨À?
	 * 
	 * @param sql
	 * @return
	 */
	public static List<User> query(String sql, Product product) {

		List<User> userList = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			User user = new User();
			user.setName("User" + i);
			user.setEmail("aa@bb.com");
			user.addProducts(product);
			userList.add(user);
		}
		return userList;
	}

}
