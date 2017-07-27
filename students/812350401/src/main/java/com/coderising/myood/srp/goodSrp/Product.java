package com.coderising.myood.srp.goodSrp;



public class Product {
	
	private String id;
	private String desc;
	public String getDescription(){
		return desc;
	}


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

	@Override
	public String toString() {
		return desc;
	}
}
