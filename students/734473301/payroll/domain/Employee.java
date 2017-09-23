package com.jyz.payroll.domain;

import java.util.Date;
// 雇员类
public class Employee {
	private String id;
	private String name;
	private String address;
	private Affiliation affiliation;
	

	private PaymentClassification classification; // 计算薪水
	private PaymentSchedule schedule; // 支付周期

	private PaymentMethod paymentMethod; // 该雇员的支付 方式
	// 支票邮寄到他们指定的邮政地址，也可以保存在财务那里随时支取，或者要求直接存入他们指定的银行账户

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

	// 重复发薪校验
	public void payDay(Paycheck pc){
		 double grossPay = classification.calculatePay(pc);
		 double deductions = affiliation.calculateDeductions(pc);
		 double netPay = grossPay - deductions;
		 pc.setGrossPay(grossPay);
		 pc.setDeductions(deductions);
		 pc.setNetPay(netPay);
		 System.out.println("员工："+name);
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

	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}
}

