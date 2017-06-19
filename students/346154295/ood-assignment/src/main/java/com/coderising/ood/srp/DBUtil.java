package com.coderising.ood.srp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param productID
	 * @return
	 */
	public static List<HashMap<String,String>> loadMailingList(String productID){
		String sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productID + "' "
				+ "and send_mail=1 ";
		System.out.println("loadQuery set");
		List<HashMap<String,String>> userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			HashMap userInfo = new HashMap();
			userInfo.put("NAME", "User" + i);			
			userInfo.put("EMAIL", "aa@bb.com");
			userList.add(userInfo);
		}

		return userList;
	}
}
