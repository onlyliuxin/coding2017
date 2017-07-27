package com.coderising.ood.srp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	static ProductUtil productUtil=new ProductUtil();
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
	protected static List loadMailingList() throws Exception {
		
		String sendMailQuery = LoadQuery();		
		return DBUtil.query(sendMailQuery);
	}
	protected static String LoadQuery() throws Exception {
		String productID = productUtil.getProductID();
		System.out.println("loadQuery set");
		return "Select name from subscriptions "
				+ "where product_id= '" + productID +"' "
				+ "and send_mail=1 ";		
		
	}
}
