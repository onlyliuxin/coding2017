package com.coderising.ood.mytest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List query(String sql){
		
		List userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			HashMap userInfo = new HashMap();
			userInfo.put("NAME", "User" + i);			
			userInfo.put("EMAIL", "aa@bb.com");
			userList.add(userInfo);
		}

		return userList;
	}

	public static String loadQuery(String productID) throws Exception {

		String sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productID + "' "
				+ "and send_mail=1 ";


		System.out.println("loadQuery set");
		return sendMailQuery;
	}
}
