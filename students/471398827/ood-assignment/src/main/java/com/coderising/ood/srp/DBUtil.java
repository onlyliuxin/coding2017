package com.coderising.ood.srp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List query(String sql){
		
		List userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			String userName = "User" + i;
			String email = "aa@bb.com";
			User user = new User(userName, email);
			userList.add(user);
		}

		return userList;
	}
}
