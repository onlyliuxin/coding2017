package com.jyz.payroll.domain;

import java.util.Date;

/**
 * 时间卡，小时工用
 */
public class TimeCard {
	private Date date;
	private int hours;

	public TimeCard(Date date, int hours) {
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
