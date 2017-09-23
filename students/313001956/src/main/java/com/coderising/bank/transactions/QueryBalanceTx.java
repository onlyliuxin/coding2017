package com.coderising.bank.transactions;

import com.coderising.atm.ATM;

public class QueryBalanceTx extends TransactionBase{

	public QueryBalanceTx(int account, int password) {
		super(account, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean preProcess(ATM atm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean postProcess(ATM atm) {
		// TODO Auto-generated method stub
		return false;
	}


}
