package com.coderising.ood.srp;

import java.util.List;

public class SubscriptionDao {
	
	public List loadMailingList(String productID) throws Exception {
		String sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productID +"' "
				+ "and send_mail=1 ";
		
		System.out.println("loadQuery set");
		return DBUtil.query(sendMailQuery);
	}
}
