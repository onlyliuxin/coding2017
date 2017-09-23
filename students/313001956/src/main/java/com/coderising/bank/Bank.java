package com.coderising.bank;

import java.util.ArrayList;
import java.util.List;

import com.coderising.atm.CardReader;
import com.coderising.bank.transactions.Transaction;

public class Bank {

	List<Account> accounts;

	public Bank() {
		accounts = new ArrayList<>();
		Account account = new Account(12345678, 1234);
		account.setBalance(5000);
		accounts.add(account);
	}

	public int process(Transaction tx) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean verify(int account, int password) {
		Account acc = getAccount(account);
		if (acc != null) {
			return acc.getPassword() == password;
		}
		return false;
	}

	public Account getAccount(int faccount) {
		for (Account acc : accounts) {
			if (acc.getFaccount() == faccount) {
				return acc;
			}
		}

		return null;
	}

	public void FreezeAccount(int faccount) {
		getAccount(faccount).setIsfreeze(true);
	}
}
