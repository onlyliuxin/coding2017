package com.coderising.payroll;

public class MailMethod implements PaymentMethod {
	private String address = "";
	
	public MailMethod(String address) {
		super();
		this.address = address;
	}

	@Override
	public void pay(Paycheck pc) {
		System.out.println("已将支票邮寄到"+address);
		System.out.println("应付" + pc.getGrossPay());
		System.out.println("扣除" + pc.getDeductions());
		System.out.println("实付" + pc.getNetPay());
	}

}
