package com.coderising.ood.srp.dao;

import com.coderising.ood.srp.util.DBUtil;

import java.util.List;

/**
 * Created by justin on 17/6/19.
 */
public class UserDao {

	public List querySubscriptions(String productId) {

		String sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productId +"' "
				+ "and send_mail=1 ";


		System.out.println("loadQuery set");
		return DBUtil.query(sendMailQuery);
	}
}
