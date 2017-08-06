package com.coderising.payroll;

public class BankMethod implements PaymentMethod{
	private String account;
	public void pay(Paycheck pc){
		System.out.println("转到银行账号："+account+"。备注："+pc);
	}
}
