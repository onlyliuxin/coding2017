package com.coderising.payroll;

import java.util.Date;

public class TimeCard {
	private Date date;
	private int hours;
	
	public TimeCard(Date date, int hours) {
		super();
		this.date = date;
		this.hours = hours;
	}
	
	public Date getDate() {
		return date;
	}
	public int getHours() {
		return hours;
	}
}
