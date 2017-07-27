package com.github.wluqing.coding2017.basic.ood.payroll;

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
		return false;
	}

	public Date getPayPeriodStartDate(Date d) {
		return null;
	}

	public void payDay(Paycheck pc){
		 
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

