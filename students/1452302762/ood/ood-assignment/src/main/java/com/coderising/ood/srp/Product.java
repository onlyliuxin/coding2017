package com.coderising.ood.srp;


public class Product {
	private String productID ;
	private String productDesc;
	
	public Product(String productID, String productDesc) {
		this.productID = productID;
		this.productDesc = productDesc;
	}
	public Product(String[] data) {
		this.productID =data[0];
		this.productDesc = data[1];
		System.out.println("产品ID = " + productID + "\n");
		System.out.println("产品描述 = " + productDesc + "\n");
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
