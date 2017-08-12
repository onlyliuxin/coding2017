package com.coderising.ood.srp.domain;

/**
 * 订阅者
 */
public class Subscriber {

	private String subscriberId;
	private String name;
	private String email;
	private Product product;
	private Integer sendStatus;

	public Subscriber(String subscriberId, String name, String email,
			Product product) {
		super();
		this.subscriberId = subscriberId;
		this.name = name;
		this.email = email;
		this.product = product;
	}

	public Subscriber() {
		super();
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	@Override
	public String toString() {
		return "Subscriber [subscriberId=" + subscriberId + ", name=" + name
				+ ", email=" + email + ", product=" + product + ", sendStatus="
				+ sendStatus + "]";
	}

}
