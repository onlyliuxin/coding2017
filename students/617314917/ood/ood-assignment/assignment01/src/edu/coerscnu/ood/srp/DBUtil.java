package edu.coerscnu.ood.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {

	/**
	 * 获取用户列表，应该从数据库读， 但是简化为直接生成。
	 * 
	 * @param sql
	 * @return
	 */
	public static List<HashMap<String, String>> query(String sql) {

		List<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();
		for (int i = 1; i <= 3; i++) {
			HashMap<String, String> userInfo = new HashMap<String, String>();
			userInfo.put(UserService.NAME_KEY, "User" + i);
			userInfo.put(UserService.MAIL_KEY, i + "aa@bb.com");
			userList.add(userInfo);
		}
		return userList;
	}
}
