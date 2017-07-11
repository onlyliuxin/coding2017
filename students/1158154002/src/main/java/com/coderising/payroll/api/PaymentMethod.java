package com.coderising.payroll.api;

import com.coderising.payroll.Paycheck;

public interface PaymentMethod {
	public void pay(Paycheck pc);
}
