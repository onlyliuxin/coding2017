package com.coderising.myood.payroll.my_payroll.domain;

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
		return schedule.isPayDate(d);
	}

	public Date getPayPeriodStartDate(Date d) {
		return schedule.getPayPeriodStartDate(d);
	}

	public void payDay(Paycheck pc){
		// TODO: 16/9/2017


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

