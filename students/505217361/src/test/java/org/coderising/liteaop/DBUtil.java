package test.java.org.coderising.liteaop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	/* 
	 * 数据库交互类
	 * */
	
	// 假设这个是获取人员的数据库交互
	public List executeQuery(String sql){
		System.out.println("execute sql ");
		List userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			
			User user = new User();
			user.setUsername("User"+i);
			user.setEmailadd("aa@bb.com");
			userList.add(user);
			
		}

		return userList;
		
	}
	
}
