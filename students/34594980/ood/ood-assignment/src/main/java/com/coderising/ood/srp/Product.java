package com.coderising.ood.srp;

public class Product {
	
	private String id;  //产品ID
	private String desc; //产品描述 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	public Product() {
	}
	public Product(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "产品ID=" + id + "\n产品描述 = " + desc;
	}

}
