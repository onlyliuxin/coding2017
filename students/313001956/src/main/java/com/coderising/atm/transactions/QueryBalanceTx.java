package com.coderising.atm.transactions;

import com.coderising.atm.ATM;

public class QueryBalanceTx extends TransactionBase{

	public QueryBalanceTx(int account, int password, String type) {
		super(account, password, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean preProcess(ATM atm) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean postProcess(ATM atm) {
		// TODO Auto-generated method stub
		return true;
	}

public String toNetworkPackage() {
		
		return super.toNetworkPackage();
	}
}
