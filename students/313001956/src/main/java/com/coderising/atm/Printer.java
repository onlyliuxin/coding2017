package com.coderising.atm;

import java.util.List;

import com.coderising.atm.transactions.Transaction;
import com.coderising.atm.transactions.TransactionBase;

public class Printer {

	public void print(List<TransactionBase> transactions) {
		for (int i = 0; i < transactions.size(); i++) {
			System.out.println(transactions.get(i).toNetworkPackage()); 
		}
		
	}

}
