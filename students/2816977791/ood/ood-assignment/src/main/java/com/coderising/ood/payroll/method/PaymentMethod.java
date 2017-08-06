package com.coderising.ood.payroll.method;

import com.coderising.ood.payroll.Paycheck;

public interface PaymentMethod{
	public void pay(Paycheck pc, int employId);
}
