package com.coderising.ood.srp;

public class Product {
	
	private String productID;
	private String productDesc;
	
	public Product(String productID,String productDesc){
		this.productID = productID;
		this.productDesc = productDesc;
	}

	public String getProductID() {
		return productID;
	}

	public String getProductDesc() {
		return productDesc;
	}
	
	

}
