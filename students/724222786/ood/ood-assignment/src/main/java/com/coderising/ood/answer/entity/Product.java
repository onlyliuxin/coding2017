package com.coderising.ood.answer.entity;

import java.io.Serializable;

/**
 * 产品类
 * @author readke
 *
 */
public class Product implements Serializable{
	private static final long serialVersionUID = 409352331475497580L;
	
	private String pId;
	private String pDec;
	
	public Product() {
		
	}
	public Product(String pId, String pDec) {
		super();
		this.pId = pId;
		this.pDec = pDec;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getpDec() {
		return pDec;
	}
	public void setpDec(String pDec) {
		this.pDec = pDec;
	}
	
	
}
