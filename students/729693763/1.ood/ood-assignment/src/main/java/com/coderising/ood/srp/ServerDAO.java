
package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 
 * @author  作者 Denny
 * @date 创建时间：Jun 25, 2017 2:20:04 PM 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  */
public class ServerDAO {
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
