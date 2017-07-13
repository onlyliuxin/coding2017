package com.coderising.payroll;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommissionedClassification implements PaymentClassification {
	
	double salary;
	double rate;
	Map<Date,SalesReceipt> receipts;
	
	public CommissionedClassification(double salary, double rate) {
		super();
		this.salary = salary;
		this.rate = rate;
		this.receipts = new HashMap<Date,SalesReceipt>();
	}
	
	public void addSalesReceipt(SalesReceipt sr){
		receipts.put(sr.getSaleDate(), sr);
	}
	
	@Override
	public double calculatePay(Paycheck pc) {
		double commission = 0;
		for (SalesReceipt salesReceipt : receipts.values()) {
			if(DateUtil.between(salesReceipt.getSaleDate(),
					pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				commission += salesReceipt.getAmount() * rate;
			}
		}
		return commission + salary;
	}

}
