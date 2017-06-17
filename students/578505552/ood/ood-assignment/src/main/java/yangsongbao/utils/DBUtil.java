package yangsongbao.utils;
import yangsongbao.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List<UserInfo> query(String sql){
		
		List<UserInfo> userList = new ArrayList<UserInfo>();
		for (int i = 1; i <= 3; i++) {
			UserInfo userInfo = new UserInfo();
			userInfo.setName("User" + i);
			userInfo.setEmail("aa" + i + "@bb.com");
			userList.add(userInfo);
		}
		return userList;
	}
}
