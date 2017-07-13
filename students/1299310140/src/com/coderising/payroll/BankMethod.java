package com.coderising.payroll;

public class BankMethod implements PaymentMethod {
	private String bank = "";
	private String account = "";
	
	public BankMethod(String bank, String account) {
		super();
		this.bank = bank;
		this.account = account;
	}

	@Override
	public void pay(Paycheck pc) {
		System.out.println("已将工资转入" + bank + "的" + account + "账户");
		System.out.println("应付" + pc.getGrossPay());
		System.out.println("扣除" + pc.getDeductions());
		System.out.println("实付" + pc.getNetPay());
	}

}
