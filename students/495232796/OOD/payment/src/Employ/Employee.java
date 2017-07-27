package Employ;

import java.util.Date;

import Affiliation.Affiliation;
import PayCheck.PayCheck;
import PaymentClassification.PaymentClassification;
import PaymentMethod.PaymentMethod;
import PaymentSchedule.PaymentSchedule;

public class Employee {
	private String id;
	private String name;
	private String address;
	PaymentClassification payment;
	PaymentSchedule paySch;
	PaymentMethod payMethod;
	Affiliation af;
	
	public Employee(String name, String address) {
		this.name = name;
		this.address = address;
	}
	public boolean isPayDay(Date d) {
		return this.paySch.isPayDay(d);
	}
	
	public Date getPayPeriodStartDate(Date d) {
		return this.paySch.getPayPeriodStartDate(d);
	}
	
	public void payDay(PayCheck pc) {
		double grossPay = payment.calculatePay(pc);
		double dedutions = af.calculateDeduction(pc);
		double netPay = grossPay - dedutions;
		pc.setGrossPay(grossPay);
		pc.setDeductions(dedutions);
		pc.setNetPay(netPay);
		payMethod.pay(pc);
	}
}
