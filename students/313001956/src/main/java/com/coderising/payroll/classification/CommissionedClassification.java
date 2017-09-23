package com.coderising.payroll.classification;

import java.util.Date;
import java.util.Map;

import com.coderising.payroll.domain.Paycheck;
import com.coderising.payroll.domain.PaymentClassification;
import com.coderising.payroll.domain.SalesReceipt;
import com.coderising.payroll.util.DateUtil;

public class CommissionedClassification implements PaymentClassification {
	double salary;
	double rate;

	public CommissionedClassification(double salary, double rate) {
		this.salary = salary;
		this.rate = rate;
	}

	Map<Date, SalesReceipt> receipts;

	public void AddSalesReceipt(SalesReceipt receipt){
		receipts.put(receipt.getSaleDate(), receipt);
	}
	
	@Override
	public double calculatePay(Paycheck pc) {
		int count = 0;
		for (SalesReceipt receipt : receipts.values()) {
			if (DateUtil.between(receipt.getSaleDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				count += receipt.getAmount();
			}
		}

		return salary + count* rate;
	}

}
