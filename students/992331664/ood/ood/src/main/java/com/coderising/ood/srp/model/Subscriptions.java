package com.coderising.ood.srp.model;

public class Subscriptions {

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
