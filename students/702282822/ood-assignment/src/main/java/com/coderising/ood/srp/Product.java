package com.coderising.ood.srp;

public class Product {
	
	private String productID; 
	private String productDesc;
	
	
	protected void setProductID(String ID) 
	{ 
		productID = ID; 
		
	} 

	protected String getProductID() 
	{
		return productID; 
	}
	
	protected void setProductDesc(String desc) {
		productDesc = desc;		
	}
	
	protected String getProductDesc(){
		return productDesc;		
	}

	

}
