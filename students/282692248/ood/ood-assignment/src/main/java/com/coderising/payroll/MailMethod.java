package com.coderising.payroll;

public class MailMethod implements PaymentMethod{
	private String address;
	public void pay(Paycheck pc){
		System.out.println("邮寄到："+address+"。备注："+pc);
	}
}
