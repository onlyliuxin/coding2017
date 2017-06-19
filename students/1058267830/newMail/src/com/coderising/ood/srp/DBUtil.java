package com.coderising.ood.srp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("all")
public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List<User> queryUserList(String sql){
		
		List<User> userList = new ArrayList();
		
		for (int i = 1; i <= 3; i++) {
			User user = new User("User" + i, "aa@bb.com");
			userList.add(user);
		}

		return userList;
	}
}
