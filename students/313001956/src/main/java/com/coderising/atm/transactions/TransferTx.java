package com.coderising.atm.transactions;

import com.coderising.atm.ATM;

public class TransferTx extends TransactionBase {
	int toAccount;
	int transferMoney;
	public TransferTx(int account, int password, String type, int toAccount, int transferMoney) {
		super(account, password, type);
		// TODO Auto-generated constructor stub
		this.toAccount=toAccount;
		this.transferMoney=transferMoney;
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
		
		return super.toNetworkPackage() +"|"+getToAccount()+"|"+getTransferMoney();
	}

public int getToAccount() {
	return toAccount;
}

public int getTransferMoney() {
	return transferMoney;
}
}
