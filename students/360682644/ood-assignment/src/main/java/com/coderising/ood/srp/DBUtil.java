package com.coderising.ood.srp;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public static List<MailReceiver> query(String sql, Product product){
		List<MailReceiver> userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			MailReceiver receiver = new MailReceiver();
			receiver.setName("User" + i);
			receiver.setEmail("aa@bb.com");
			receiver.setMessage(product);
			receiver.setSubject(product);
			userList.add(receiver);
		}
		return userList;
	}
}
