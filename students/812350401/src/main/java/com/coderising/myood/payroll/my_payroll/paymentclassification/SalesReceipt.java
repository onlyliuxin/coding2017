package com.coderising.myood.payroll.my_payroll.paymentclassification;

import com.coderising.myood.payroll.my_payroll.util.DateUtil;

import java.util.Date;

public class SalesReceipt {
	private Date saleDate;
	private double amount;

	public SalesReceipt(String dateStr, double amount) {
        this.saleDate = DateUtil.parseDate(dateStr);
        this.amount = amount;
    }

	public Date getSaleDate() {
		return saleDate;
	}
	public double getAmount() {
		return amount;
	}
}
