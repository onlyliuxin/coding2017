package com.coderising.payroll;

import com.coderising.payroll.api.PaymentClassification;

public class SalariedClassification implements PaymentClassification{
	private double salary;
	
	public SalariedClassification(double salary) {
		this.salary=salary;
	}

	@Override
	public double calculatePay(Paycheck pc) {
		// TODO Auto-generated method stub
		return salary;
	}

}
