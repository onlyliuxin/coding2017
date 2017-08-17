package lilei.com.cn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 /**
  * DBUtil 连接数据库，获取数据
  * 个人理解，此类只是负责数据库的连接，和数据的读取，存储，修改
  * 
  * */
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
