package com.coderising.atm.transactions;

import com.coderising.atm.ATM;

public class DepositTx extends TransactionBase {

	private int acctualMoney;
	
	public DepositTx(int account, int password, String type) {
		super(account, password, type);
	}

	@Override
	public boolean preProcess(ATM atm) {
		// TODO Auto-generated method stub
	 acctualMoney=	 atm.retriveMoney();
		 
		 return true;
	}

	@Override
	public boolean postProcess(ATM atm) {
		// TODO Auto-generated method stub
		return true;
	}

	public String toNetworkPackage() {
		
		return super.toNetworkPackage() +"|"+getAcctualMoney();
	}
	
	public int getAcctualMoney() {
		return acctualMoney;
	}

}
