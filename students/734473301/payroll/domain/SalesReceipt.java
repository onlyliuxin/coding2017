package com.jyz.payroll.domain;

import java.util.Date;

/**
 * 销售凭条
 */
public class SalesReceipt {
	private Date saleDate;
	private double amount;

	public SalesReceipt(Date saleDate, double amount) {
		this.saleDate = saleDate;
		this.amount = amount;
	}

	public Date getSaleDate() {
		return saleDate;
	}
	public double getAmount() {
		return amount;
	}
}
