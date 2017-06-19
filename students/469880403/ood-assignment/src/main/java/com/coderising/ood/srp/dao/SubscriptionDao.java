package com.coderising.ood.srp.dao;

import java.util.List;

import com.coderising.ood.srp.util.DBUtil;

public class SubscriptionDao {
	
	protected static String sendMailQuery = null;
	public  List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}

	public  void setLoadQuery(String productID) throws Exception {

		sendMailQuery = "Select name from subscriptions " + "where product_id= '" + productID + "' "
				+ "and send_mail=1 ";

		System.out.println("loadQuery set");
	}
	
}
