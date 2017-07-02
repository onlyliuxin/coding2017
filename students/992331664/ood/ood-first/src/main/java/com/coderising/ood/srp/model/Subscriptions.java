package com.coderising.ood.srp.model;

/**
 * 订阅信息，主要有订阅产品，订阅用户
 * 
 */
public class Subscriptions {

	private User user;
	private Product product;

	public Subscriptions() {
		super();
	}

	public Subscriptions(User user, Product product) {
		super();
		this.user = user;
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Subscriptions [user=" + user + ", product=" + product + "]";
	}
}
