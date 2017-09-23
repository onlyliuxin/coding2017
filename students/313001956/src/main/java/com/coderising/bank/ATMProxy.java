package com.coderising.bank;

import org.junit.experimental.theories.Theories;

import com.coderising.atm.CardReader;
import com.coderising.bank.transactions.Transaction;

public class ATMProxy {
	private Bank bank;

	public ATMProxy(Bank bank) {
		this.bank = bank;
	}

	public void run() {
		// ???????
		String data = "";
		String response = "";
		process(data, response);
	}

	public void process(String data, String response) {
		Transaction tx = toActObject(data);
		int status = bank.process(tx);
		// response.write(status);
	}

	private Transaction toActObject(String data) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean verify(int account, int password) {

		return this.bank.verify(account, password);
	}

	public void FreezeAccount(int faccount) {
		bank.FreezeAccount(faccount);
	}
}
