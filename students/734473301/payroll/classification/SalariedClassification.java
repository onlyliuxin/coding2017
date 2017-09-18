package com.jyz.payroll.classification;

import com.jyz.payroll.domain.Paycheck;
import com.jyz.payroll.domain.PaymentClassification;

/**
 * 月薪族
 */
public class SalariedClassification implements PaymentClassification {
	private double salary;
	public SalariedClassification(double salary){
		this.salary = salary;
	}
	@Override
	public double calculatePay(Paycheck pc) {		
		return salary;
	}

}
