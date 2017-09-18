package com.coderising.atm.transactions;

public abstract class TransactionBase implements Transaction{
	int account;
	int password;
	String type;
	
	
	public TransactionBase(int account,int password, String type) {
		this.account=account;
		this.password=password;
		this.type=type;
	}
	
	public int getAccount() {
		return account;
	}

	public String toNetworkPackage() {
		// TODO Auto-generated method stub
		return getType() +"|"+getAccount();
	}

	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

}
