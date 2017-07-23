package com.coderising.payroll;

import java.util.Date;

public class WeeklySchedule implements PaymentSchedule{
	public boolean isPayDate(Date date){
		return DateUtil.isFriday(date);
	}
	public Date getPayPeriodStartDate( Date payPeriodEndDate){
		return DateUtil.add(payPeriodEndDate, -6);
	}
}
