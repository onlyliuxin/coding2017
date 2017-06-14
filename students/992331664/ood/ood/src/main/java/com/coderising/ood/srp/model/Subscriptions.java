package com.coderising.ood.srp.model;

/**
 * 订阅信息，主要有订阅产品，订阅用户
 * 
 */
public class Subscriptions {

	// name 和 email 应该存放在用户信息中，如叫订阅用户，
	private String name;
	private String email;
	private String productId;
	private Product product;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Subscriptions [name=" + name + ", email=" + email + ", productId=" + productId + ", product=" + product
				+ "]";
	}

}
