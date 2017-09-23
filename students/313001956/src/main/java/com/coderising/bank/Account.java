package com.coderising.bank;

public class Account {

	private int faccount;
	private int password;
	private int balance;
	private boolean isfreeze;

	public Account(int faccount, int password) {
		this.faccount = faccount;
		this.password = password;
		// TODO Auto-generated constructor stub
	}

	public int getFaccount() {
		return faccount;
	}

	public int getPassword() {
		return password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setIsfreeze(boolean isfreeze) {
		this.isfreeze = isfreeze;
	}

	public boolean getIsfreeze() {
		return this.isfreeze;
	}
}
