package com.coderising.bank.transactions;

import com.coderising.atm.ATM;

public class TransferTx extends TransactionBase {

	public TransferTx(int account, int password) {
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
