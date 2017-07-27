package com.coderising.ood.srp.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 模拟获取数据库
 * <p>标题: </p>
 * <p>描述: </p>
 * @autho zx
 * @time 2017年6月12日 下午11:47:21
*/
public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List query(String sql){
		
		List userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			HashMap userInfo = new HashMap();
			userInfo.put("NAME", "User" + i);			
			userInfo.put("EMAIL", "aa@bb.com");
			userList.add(userInfo);
		}

		return userList;
	}
}
