package com.coderising.ood.answer.utils;

import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.answer.entity.User;

/**
 * db工具
 * @author readke
 *
 */
public class DBUtils {
	
	public static List<User> queryByProductID(String productID){
		/**
		 * sql = "Select name from subscriptions "
				+ "where product_id= '" + productID +"' "
				+ "and send_mail=1 ";
		 */
		List<User> userList = new ArrayList<User>();
		
		for (int i = 1; i <= 3; i++) {
			User userInfo = new User();
			userInfo.setName("User" + i);			
			userInfo.setEmail("aa@bb.com");
			userList.add(userInfo);
		}

		return userList;
	}
}
