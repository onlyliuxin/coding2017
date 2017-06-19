package com.coderising.ood.srp;

import java.util.ArrayList;
public class User {
	private String name;
	private String email;

	
	/**
	 * 通过降价商品的id 获取需要返回的用户
	 * @param productID
	 * @return
	 */
	public ArrayList<User> getUserById(String productID){
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(new User("高欢1","15582372277@163.com"));
		userList.add(new User("高欢2"));
		userList.add(new User("高欢3"));
		return userList;
	}
	
	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public User(String name) {
		super();
		this.name = name;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
