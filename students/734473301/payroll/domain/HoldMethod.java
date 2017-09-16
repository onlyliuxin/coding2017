package com.jyz.payroll.domain;

public class HoldMethod implements PaymentMethod {

	@Override
	public void pay(Paycheck pc) {
		
        System.out.println("支付记录："+pc);
	}

}
