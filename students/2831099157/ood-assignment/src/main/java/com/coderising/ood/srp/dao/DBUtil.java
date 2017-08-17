package com.coderising.ood.srp.dao;
import com.coderising.ood.srp.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List<User> query(String sql){
		
		List userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			User user = new User();
			user.setName("User" + i);
			user.seteMail("aa@bb.com");
			userList.add(user);
		}

		return userList;
	}
}
