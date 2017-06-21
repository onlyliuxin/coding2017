package com.coderising.ood.srp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {

	protected String sendMailQuery = null;
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param productID
	 * @return
	 */
	public static List query(String productID){
		
		List userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			HashMap userInfo = new HashMap();
			userInfo.put("NAME", "User" + i);			
			userInfo.put("EMAIL", "aa@bb.com");
			userList.add(userInfo);
		}

		return userList;
	}

	protected void setLoadQuery(String productID) throws Exception {

		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productID + "' "
				+ "and send_mail=1 ";


		System.out.println("loadQuery set");
	}
}
