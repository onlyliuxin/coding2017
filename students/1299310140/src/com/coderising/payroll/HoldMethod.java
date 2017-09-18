package com.coderising.payroll;

public class HoldMethod implements PaymentMethod {

	@Override
	public void pay(Paycheck pc) {
		System.out.println("工资保存在财务那，可随时支取");
		System.out.println("应付" + pc.getGrossPay());
		System.out.println("扣除" + pc.getDeductions());
		System.out.println("实付" + pc.getNetPay());
	}

}
