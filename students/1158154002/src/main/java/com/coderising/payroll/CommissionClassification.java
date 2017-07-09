package com.coderising.payroll;

import java.util.Date;
import java.util.Map;

import com.coderising.payroll.api.PaymentClassification;
import com.coderising.payroll.util.DateUtil;

public class CommissionClassification implements PaymentClassification{
	private double salary;
	private double rate;
	private Map<Date,SalesReceipt> receipts;
	
	public CommissionClassification(double rate, double salary) {
		this.rate=rate;
		this.salary=salary;
	}
	
	public void addSalesReceipt(SalesReceipt sr) {
		receipts.put(sr.getSaleDate(), sr);
	}

	@Override
	public double calculatePay(Paycheck pc) {
		double commission=0;
		for (SalesReceipt sr : receipts.values()) {
			if (DateUtil.between(sr.getSaleDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				commission+=sr.getAmount()*rate;
			}
		}
		return salary+commission;
	}

}
