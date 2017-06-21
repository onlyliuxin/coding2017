package main.java.com.coderising.ood.srp;

import java.util.List;

public class LoadInformation {

	private String productID = new ProductInfo().getproductID();
	
	private String sendMailQuery = null;
	
	protected List<?> loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}

	protected void setLoadQuery() throws Exception {

		sendMailQuery = "Select name from subscriptions " + "where product_id= '" + productID + "' " + "and send_mail=1 ";

		System.out.println("loadQuery set");
	}

}
