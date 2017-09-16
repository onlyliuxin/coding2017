package com.jyz.payroll.domain;

public interface PaymentClassification {
	/**
	 * 计算薪水
	 * @param pc
	 * @return
	 */
	double calculatePay(Paycheck pc);
}
