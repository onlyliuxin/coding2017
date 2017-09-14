package com.coderising.atm.transactions;

import com.coderising.atm.ATM;

public interface Transaction {
	public static final String EXIT = "E";
	public static final String DEPOSIT = "D";
	public static final  String WITHDRAW = "W";
	public static final String TRANFER = "T";
	public static final String BALANCE = "B";
	public static final String PRINT = "P";
	

	public boolean preProcess(ATM atm);

	public boolean postProcess(ATM atm);

	public String toNetworkPackage();
	}
