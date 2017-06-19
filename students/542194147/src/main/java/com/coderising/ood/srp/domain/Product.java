package com.coderising.ood.srp.domain;

import java.io.Serializable;

/**
 * 产品实体类
 * @author 小摩托
 *
 */
public class Product implements Serializable {

	private String productID = null;
	private String productDesc = null;
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
