package ood.work.srp;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List<User> query(String sql){
		
		List<User> userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			User userInfo = new User();
			userInfo.setName("User" + i);
			userInfo.setEmail("aa"+ i +"@bb.com");
			userList.add(userInfo);
		}

		return userList;
	}
}
