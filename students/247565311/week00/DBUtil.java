package week00;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class DBUtil {
	/**
	 * Ӧ�ô����ݿ���� ���Ǽ�Ϊֱ�����ɡ�
	 * @param sql
	 * @return
	 */
	public static List<HashMap<String,String>> query(String sql){
		List<HashMap<String,String>> userList = new ArrayList<HashMap<String,String>>();
		for (int i = 1; i <= 3; i++) {
			HashMap<String,String> userInfo = new HashMap<String,String>();
			userInfo.put("NAME", "User" + i);			
			userInfo.put("EMAIL", "aa@bb.com");
			userList.add(userInfo);
		}
		return userList;
	}
}
