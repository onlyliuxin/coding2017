package com.coderising.ood.srp.common;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.coderising.ood.srp.entity.User;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List<User> query(String sql){
		
		List<User> userList = new ArrayList<User>();
		for (int i = 1; i <= 3; i++) {
			User user = new User();
			user.setUserName("User" + i);
			user.setEmailAddress("aa"+i+"@bb.com");
			userList.add(user);
		}
		return userList;
	}
}
