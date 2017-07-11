package com.coderising.payroll;

import com.coderising.payroll.api.PaymentMethod;

public class MailMethod implements PaymentMethod{
	private String address;
	
	@Override
	public void pay(Paycheck pc) {
		System.out.println("MailMethod");
	}

}
