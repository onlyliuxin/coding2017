package com.coderising.ood.srp.chasing.util;
import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp.chasing.model.User;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List<User> query(String sql){
		List<User> userList = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			userList.add(new User("User" + i,"aa@bb.com"));
		}
		return userList;
	}
}
