package com.github.wluqing.coding2017.basic.ood.srp.good1;

import java.util.List;



public class User {
	
	private String name;
	private String emailAddress;
	
	private List<Product> subscribedProducts; 
	
	public String getName(){
		return name;
	}
	public String getEMailAddress() {		
		return emailAddress;
	}
	public List<Product> getSubscribedProducts(){
		return this.subscribedProducts;
	}
	
}
