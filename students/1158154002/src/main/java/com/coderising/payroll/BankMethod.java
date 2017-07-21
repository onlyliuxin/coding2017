package com.coderising.payroll;

import com.coderising.payroll.api.PaymentMethod;

public class BankMethod implements PaymentMethod{
	
	private String bank;
	private String account;
	
	@Override
	public void pay(Paycheck pc) {
		System.out.println("BankMethod");
	}

}
