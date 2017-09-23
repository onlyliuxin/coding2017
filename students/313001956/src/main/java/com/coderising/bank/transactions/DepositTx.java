package com.coderising.bank.transactions;

import com.coderising.atm.ATM;

public class DepositTx extends TransactionBase {

	public DepositTx(int account, int password) {
		super(account, password);
	}

	@Override
	public boolean preProcess(ATM atm) {
		// TODO Auto-generated method stub
	int acctualMoney=	 atm.retriveMoney();
		 
		 return false;
	}

	@Override
	public boolean postProcess(ATM atm) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
