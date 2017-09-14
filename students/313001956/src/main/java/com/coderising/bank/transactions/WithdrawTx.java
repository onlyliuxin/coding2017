package com.coderising.bank.transactions;

import com.coderising.atm.ATM;

public class WithdrawTx extends TransactionBase {

	int amount;
	
	public WithdrawTx(int account,int password,int amount) {
		super(account, password);
		this.amount=amount;
	}
	
	@Override
	public boolean preProcess(ATM atm) {
		// TODO Auto-generated method stub
		return atm.hashEnoughMoney(getAmount());
	}

	private int getAmount() {
		// TODO Auto-generated method stub
		return amount;
	}

	@Override
	public boolean postProcess(ATM atm) {
		// TODO Auto-generated method stub
		return atm.dispenseMoney(getAmount());
	}


}
