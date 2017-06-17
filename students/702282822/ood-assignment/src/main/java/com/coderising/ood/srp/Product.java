package com.coderising.ood.srp;

public class Product {
	
	private static String productID; 
	private static String productDesc;
	
	
	protected static void setProductID(String ID) 
	{ 
		productID = ID; 
		
	} 

	protected static String getProductID() 
	{
		return productID; 
	}
	
	protected static void setProductDesc(String desc) {
		productDesc = desc;		
	}
	
	protected static String getProductDesc(){
		return productDesc;		
	}

	

}
