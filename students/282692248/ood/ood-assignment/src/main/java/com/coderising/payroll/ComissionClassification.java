package com.coderising.payroll;

import java.util.ArrayList;
import java.util.List;

public class ComissionClassification implements PaymentClassification {
	private double salary;
	private double rate;
	List<SalesReceipt> receipts = new ArrayList<>();
	
	public ComissionClassification(double salary, double rate, List<SalesReceipt> receipts) {
		super();
		this.salary = salary;
		this.rate = rate;
		this.receipts = receipts;
	}


	public double calculatePay(Paycheck pc){
		double totalAmount = 0.0;
		for(SalesReceipt sr:receipts){
			if(DateUtil.between(sr.getSaleDate(),pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())){
				totalAmount += sr.getAmount();
			}
		}
		return salary + totalAmount*rate;
	}
}
