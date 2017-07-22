package com.coderising.payroll;

import java.util.Date;

public class Employee {
	String id;
	String name;
	String address;
	Affiliation affiliation = new NonAffiliation();
	

	PaymentClassification classification;
	PaymentSchedule schedule;
	PaymentMethod paymentMethod;

	public Employee(String id, String name, String address){
		this.id = id;
		this.name = name;
		this.address = address;
	}
	public boolean isPayDay(Date d) {
		return schedule.isPayDate(d);
	}

	public Date getPayPeriodStartDate(Date d) {
		return schedule.getPayPeriodStartDate(d);
	}

	public void payDay(Paycheck pc){
		 double grossPay = classification.calculatePay(pc);
		 double deductions = affiliation.calculateDeductions(pc);
		 double netPay = grossPay - deductions;
		 pc.setGrossPay(grossPay);
		 pc.setDeductions(deductions);
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
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
}

