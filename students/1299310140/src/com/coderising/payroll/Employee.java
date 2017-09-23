package com.coderising.payroll;

import java.util.Date;

public class Employee {
	
	String id;
	String name;
	String address;
	
	Affiliation affiliation;
	PaymentClassification classification;
	PaymentSchedule schedule;
	PaymentMethod paymentMethod;

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

	public void payDay(Paycheck pc){//重点，笼统说话
		double grossPay = this.classification.calculatePay(pc);
		double deduction = this.affiliation.calculateDeductions(pc);
		double netPay = grossPay - deduction;
		pc.setGrossPay(grossPay);
		pc.setDeductions(deduction);
		pc.setNetPay(netPay);
		this.paymentMethod.pay(pc);
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
	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}
	
}

