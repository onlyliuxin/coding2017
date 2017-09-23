package com.coderising.bank.transactions;

import com.coderising.atm.ATM;

public interface Transaction {
	public boolean preProcess(ATM atm);

	public boolean postProcess(ATM atm);
	}
