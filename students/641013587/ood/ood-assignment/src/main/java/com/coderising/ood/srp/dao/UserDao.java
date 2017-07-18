package com.coderising.ood.srp.dao;
import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp.entity.User;

public class UserDao {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public  List<User> query(String sql){
		
		List<User> userList = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			User userInfo = new User();
			userInfo.setName("User" + i);
			userInfo.setEmail("aa@bb.com");
			userList.add(userInfo);
		}

		return userList;
	}
}
