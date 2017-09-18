package com.jyz.payroll.domain;

public interface Affiliation {
	/**
	 * 计算扣除
	 * @param pc
	 * @return
	 */
	 double calculateDeductions(Paycheck pc);
}
