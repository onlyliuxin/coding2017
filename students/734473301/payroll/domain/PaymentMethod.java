package com.jyz.payroll.domain;

public interface PaymentMethod {
	/**
	 * 支付方式
	 * @param pc
	 */
	void pay(Paycheck pc);
}
