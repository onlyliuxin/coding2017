package com.coderising.ood.srp.model;

/**
 * 产品信息
 */
public class Product {

	private String productID;

	private String productDesc;

	public Product() {
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

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productDesc=" + productDesc + "]";
	}

}
