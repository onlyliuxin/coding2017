package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private String email;
	private List<Product> products = new ArrayList<>();

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addProducts(Product product) {
		this.products.add(product);
	}
}
