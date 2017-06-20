package com.coderising.ood.srp.util;

import com.coderising.ood.srp.dto.Product;
import com.coderising.ood.srp.dto.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	protected String sendMailQuery = null;
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * @return
	 */
	public static List query(){
		List userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			HashMap userInfo = new HashMap();

			userList.add(new User("User"+i,"aa@bb.com"));
		}

		return userList;
	}

	protected void setLoadQuery( Product product) throws Exception {

		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + product.getProductID() + "' "
				+ "and send_mail=1 ";


		System.out.println("loadQuery set"+ "\n");
	}



}
