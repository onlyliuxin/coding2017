package com.coderising.ood.srp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	private String sendMailQuery = null;
	private String productID = null;
	private String productDesc = null;
	private File f = null;
	
	DBUtil(File f){
		try {
			readFile(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
	
	public List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}
	
	private void setLoadQuery() throws Exception {
		
		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + getProductID() +"' "
				+ "and send_mail=1 ";
		System.out.println("loadQuery set");
	}
	
	
	private void readFile(File file) throws IOException // @02C
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			setProductID(data[0]); 
			setProductDesc(data[1]); 
			
			System.out.println("产品ID = " + productID + "\n");
			System.out.println("产品描述 = " + productDesc + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}
		
		private void setProductDesc(String desc) {
			this.productDesc = desc;		
		}
		
		protected void setProductID(String productID) 
		{ 
			this.productID = productID; 
		}
		
		public String getProductDesc() {
			return productDesc;		
		}
		
		public String getProductID() {
			return productID;
		}
}
