package com.coderising.payroll;

import com.coderising.payroll.api.PaymentMethod;

public class HoldMethod implements PaymentMethod{

	@Override
	public void pay(Paycheck pc) {
		System.out.println("HoldMethod");
	}

}
