package com.coderising.ood.srp.util;
import com.coderising.ood.srp.entity.User;

import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List query(String sql){
		
		List<User> userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			User user = new User();
			user.setName("User" + i);
			user.setEmail("aa@bb.com");
			userList.add(user);
		}

		return userList;
	}
}
