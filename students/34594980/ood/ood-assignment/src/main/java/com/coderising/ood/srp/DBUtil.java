package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {

	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * 
	 * @param sql
	 * @return
	 */
	public static List<HashMap<String, String>> query(String sql) {

		List<HashMap<String, String>> userList = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			HashMap<String, String> userInfo = new HashMap<>();
			userInfo.put("NAME", "User" + i);
			userInfo.put("EMAIL", "aa@bb.com");
			userList.add(userInfo);
		}
		return userList;
	}

	public static List<HashMap<String, String>> loadMailingList(Product product) {
		
		String sendMailQuery = "Select name from subscriptions " + "where product_id= '"
				+ product.getId() + "' " + "and send_mail=1 ";
		System.out.println("loadQuery set");
		return DBUtil.query(sendMailQuery);
	}
}
