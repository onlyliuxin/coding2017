package com.coderising.myood.payroll.liuxin_payroll.classification;

import com.coderising.payroll.domain.Paycheck;
import com.coderising.payroll.domain.PaymentClassification;

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
