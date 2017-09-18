package com.coderising.payroll;

import java.util.Date;

public class ServiceCharge {
	private Date date;
	private int amount;
	
	public ServiceCharge(Date date, int amount) {
		super();
		this.date = date;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public int getAmount() {
		return amount;
	}
	
}
