package com.coderising.payroll.domain;

import java.util.Date;

public class Employee {
	private String id;
	private String name;
	private String address;
	private Affiliation affiliation;
	

	private PaymentClassification classification;
	private PaymentSchedule schedule;
	private PaymentMethod paymentMethod;

	public Employee(String name, String address){
		this.name = name;
		this.address = address;
	}
	public boolean isPayDay(Date d) {
		return this.schedule.isPayDate(d);
	}

	public Date getPayPeriodStartDate(Date d) {
		return this.schedule.getPayPeriodStartDate(d);
	}

	public void payDay(Paycheck pc){
		 double grossPay = classification.calculatePay(pc);
		 double deductions = affiliation.calculateDeductions(pc);
		 double netPay = grossPay - deductions;
		 pc.setGrossPay(grossPay);
		 pc.setDeductions(deductions);
		 pc.setNetPay(netPay);
		 paymentMethod.pay(pc);
	}
	
	public void setClassification(PaymentClassification classification) {
		this.classification = classification;
	}
	public void setSchedule(PaymentSchedule schedule) {
		this.schedule = schedule;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}

