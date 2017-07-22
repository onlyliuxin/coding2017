package com.coderising.payroll;

public class HoldMethod implements PaymentMethod{
	public void pay(Paycheck pc){
		System.out.println("转到财务处："+pc);
	}
}
