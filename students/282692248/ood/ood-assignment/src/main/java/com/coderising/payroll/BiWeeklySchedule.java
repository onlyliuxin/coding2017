package com.coderising.payroll;

import java.util.Date;

public class BiWeeklySchedule implements PaymentSchedule{
	private Date firstPayDay;
	public BiWeeklySchedule(String firstPayDay){
		this.firstPayDay = DateUtil.parseDate(firstPayDay);
	}
	
	public boolean isPayDate(Date date){
		return DateUtil.getDaysBetween(firstPayDay, date)%14 == 0;
	}
	
	public Date getPayPeriodStartDate( Date payPeriodEndDate){
		return DateUtil.add(payPeriodEndDate, -13);
	}
}
