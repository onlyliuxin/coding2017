package com.coderising.myood.payroll.my_payroll.domain;

import java.util.Date;

public class Employee {
	int id;
	String name;
	String address;
	Affiliation affiliation;


	PaymentClassification classification;
	PaymentSchedule schedule;
	PaymentMethod paymentMethod;

	public Employee(int id, String name, String address){
	    this.id = id;
		this.name = name;
		this.address = address;
	}
	public boolean isPayDay(Date d) {
		return schedule.isPayDate(d);
	}

	public Date getPayPeriodStart(Date d) {
		return schedule.getPayPeriodStartDate(d);
	}

	public void payDay(Paycheck pc){
        double grossPay = classification.calculatePay(pc);
        pc.setGrossPay(grossPay);
        double deductions = affiliation.calculateDeductions(pc);
        pc.setDeductions(deductions);
        pc.setNetPay(grossPay - deductions);
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public PaymentClassification getClassification() {
        return classification;
    }

    public PaymentSchedule getSchedule() {
        return schedule;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }
}

