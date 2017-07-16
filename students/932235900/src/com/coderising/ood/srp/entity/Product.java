package com.coderising.ood.srp.entity;
/**
 * 
 * @author liubin
 * 产品实体类
 *
 */
public class Product {
	
	private String productId;
	private String productDesc;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	@Override
	public String toString() {
		return "Product [产品ID = " + productId + ", 产品描述 = " + productDesc + "]";
	}
	
	
}
