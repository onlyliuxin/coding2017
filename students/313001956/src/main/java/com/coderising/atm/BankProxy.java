package com.coderising.atm;

import com.coderising.atm.transactions.Transaction;
import com.coderising.bank.ATMProxy;
import com.coderising.bank.Bank;

public class BankProxy {
	ATMProxy atmProxy = new ATMProxy(new Bank());

	public boolean verify(int account, int password) {

		return atmProxy.verify(account, password);
	}

	public boolean process(Transaction tx) {
		String netPackage = tx.toNetworkPackage();
		atmProxy.process(netPackage, null);
		return true;
	}

	public void FreezeAccount(int account) {

	}
}
