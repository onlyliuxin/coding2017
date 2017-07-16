package com.coderising.ood.answer.entity;

import java.io.Serializable;

/**
 * 用户类
 * @author readke
 *
 */
public class User implements Serializable{
	private static final long serialVersionUID = -7916484660512326120L;
	
	private String id;
	private String name;
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
}
