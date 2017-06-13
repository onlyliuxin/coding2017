package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserInfoServiceImpl implements UserInfoService {

	public List<Map<String, String>> getList(String productID) {
		// TODO Auto-generated method stub
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String sql = "Select name from subscriptions " + "where product_id= '" + productID + "' "
				+ "and send_mail=1 ";

		System.out.println("loadQuery set");
		try {
			list = DBUtil.query(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}
