package com.coderising.ood.srp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	
	private String sql;
	
	
    public void setLoadQuery(String productID) throws Exception {
		
		 String sql = "Select name from subscriptions "
				+ "where product_id= '" + productID +"' "
				+ "and send_mail=1 ";
		 this.setSql(sql);
		System.out.println("loadQuery set");
	}
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @param sql
	 * @return
	 */
	public List queryForList(){
		String query=this.getSql();
		List userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			User userInfo = new User();
			userInfo.setNAME("User" + i);			
			userInfo.setEMAIL("aa@bb.com");
			userList.add(userInfo);
		}
		return userList;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
}
