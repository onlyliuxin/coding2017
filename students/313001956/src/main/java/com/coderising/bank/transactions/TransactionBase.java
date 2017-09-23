package com.coderising.bank.transactions;

public abstract class TransactionBase implements Transaction{
	int account;
	int password;
	
	
	public TransactionBase(int account,int password) {
		this.account=account;
		this.password=password;
	}
	
	public int getAccount() {
		return account;
	}

}
