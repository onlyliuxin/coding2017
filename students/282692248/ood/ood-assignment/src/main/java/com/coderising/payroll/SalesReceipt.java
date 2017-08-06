package com.coderising.payroll;

import java.util.Date;

public class SalesReceipt {
	private Date saleDate;
	private double amount;
	
	
	public SalesReceipt(Date saleDate, double amount) {
		super();
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
