package com.coderising.ood.srp.chasing.model;

public class Product {
	protected String productID = null;
	protected String productDesc = null;
	
	public Product() {}
	
	public Product(String productID, String productDesc) {
		super();
		this.productID = productID;
		this.productDesc = productDesc;
	}
	
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
}
