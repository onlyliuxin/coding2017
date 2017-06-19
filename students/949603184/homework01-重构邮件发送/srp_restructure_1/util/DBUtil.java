package com.coderising.ood.srp_restructure_1.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.coderising.ood.srp_restructure_1.pojo.User;

public class DBUtil {

	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * 
	 * @param sql
	 * @return
	 */
	public static List query(String sql) {

		List userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			/*
			 * HashMap userInfo = new HashMap(); userInfo.put("NAME", "User" +
			 * i); userInfo.put("EMAIL", "aa@bb.com"); userList.add(userInfo);
			 */
			/*
			 * 因为在重构的时候使用了bean，所以为了方便直接改为返回beanlist
			 */
			User user = new User();
			user.setName("User" + i);
			user.setEmail("aa@bb.com");
			userList.add(user);
		}

		return userList;
	}
}
