package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.List;

public class Product {
	
	protected String productID = null;
	protected String productDesc = null;
	
	protected void setProductID(String productID) 
	{ 
		this.productID = productID; 
		
	} 

	protected String getproductID() 
	{
		return productID; 
	} 

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getProductID() {
		return productID;
	}

	//获得该产品的所有订阅者
	protected List<User> getLoadQuery(String productID){
		String sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productID +"' "
				+ "and send_mail=1 ";
		
		System.out.println("loadQuery set");
		return  DBUtil.query(sendMailQuery);
	}
}
