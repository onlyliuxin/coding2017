package com.coderising.ood.srp;

import java.util.List;

public class MailingDao {

	private String sendMailQuery;
	public List getQuery(String productID) throws Exception {
		
		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productID +"' "
				+ "and send_mail=1 ";
		
		
		System.out.println("loadQuery set");
		
		return loadMailingList();
	}

	private List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}
}
