package com.coderising.ood.srp;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List<UserInfo> query(String sql){
		
		List<UserInfo> userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {

			userList.add(new UserInfo("User" + i, "aa@bb.com"));
		}

		return userList;
	}
}
