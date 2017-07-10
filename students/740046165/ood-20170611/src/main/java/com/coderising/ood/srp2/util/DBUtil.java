package com.coderising.ood.srp2.util;
import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp2.model.FollowUser;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List<FollowUser> query(String productID){
		
		//查询
		try {
			setLoadQuery(productID);
		} catch (Exception e) {
			return null;
		}
		
		//模拟数据库查询结果
		List<FollowUser> userList = new ArrayList<>();
		
		if ("P8756".equals(productID)) {
			//iphone8
			userList.add(createFollowUser("1"));		
			userList.add(createFollowUser("2"));
		} else  if ("P3946".equals(productID)) { 
			//XiaoMi10
			userList.add(createFollowUser("3"));
			userList.add(createFollowUser("4"));
		} else if ("P8904".equals(productID)) {
			//Oppo_R15
			userList.add(createFollowUser("1"));
			userList.add(createFollowUser("3"));
		} else if ("P4955".equals(productID)) {
			//Vivo_X20
			userList.add(createFollowUser("4"));
			userList.add(createFollowUser("5"));
		}

		return userList;
	}
	/**
	 * 创建FollowUser
	 * @param userId
	 * @return
	 */
	private static FollowUser createFollowUser(String userId) {
		FollowUser userInfo = new FollowUser();
		userInfo.setUserName("User" + userId);		
		userInfo.setUserEmail("user" + userId + "@aabb.com");
		return userInfo;
	}
	
	
	private static String setLoadQuery(String productID) throws Exception {

		String sendMailQuery = "Select name, email from subscriptions " 
				+ "where product_id= '" + productID + "' "
				+ "and send_mail=1 ";

		System.out.println("loadQuery set: " + productID);
		return sendMailQuery;
	}
	
}
