package com.coderising.atm.transactions;

import com.coderising.atm.ATM;

public class WithdrawTx extends TransactionBase {

	int amount;
	
	public WithdrawTx(int account,int password,String type, int amount) {
		super(account, password, type);
		this.amount=amount;
	}
	
	@Override
	public boolean preProcess(ATM atm) {
		// TODO Auto-generated method stub
		boolean result= atm.hashEnoughMoney(getAmount());
		if (!result) {
			atm.getSuperKeypad().displayMessage("ATM???????");
			
		}
		
		return result ;
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

public String toNetworkPackage() {
		
		return super.toNetworkPackage() +"|"+getAmount();
	}
}
