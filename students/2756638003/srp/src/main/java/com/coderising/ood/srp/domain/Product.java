package com.coderising.ood.srp.domain;

/**
 * 商品类
 */
public class Product {

	private String productID;
	private String productDesc;

	public Product(String productID, String productDesc) {
		super();
		this.productID = productID;
		this.productDesc = productDesc;
	}

	public Product() {
		super();
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
		return "Product [productID=" + productID + ", productDesc="
				+ productDesc + "]";
	}

}
