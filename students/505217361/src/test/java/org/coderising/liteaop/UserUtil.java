package test.java.org.coderising.liteaop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserUtil {
	
	// 获取有关注相关产品信息的用户
	public List getSubscriptionUser(String productID){
		String sql = "Select name from subscriptions "
			+ "where product_id= '" + productID +"' "
			+ "and send_mail=1 ";
		
		DBUtil dbu = new DBUtil();
		List users = dbu.executeQuery(sql);
		
		System.out.println("loadQuery set");
		return users;
		
	}
	
	
}
