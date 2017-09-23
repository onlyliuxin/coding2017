package com.coderising.payroll;

public class SalariedClassification implements PaymentClassification {
	private double salary;
	
	public SalariedClassification(double salary) {
		super();
		this.salary = salary;
	}

	@Override
	public double calculatePay(Paycheck pc) {
		return salary;
	}

}
