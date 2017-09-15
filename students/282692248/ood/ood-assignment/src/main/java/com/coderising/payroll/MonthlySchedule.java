package com.coderising.payroll;

import java.util.Date;

public class MonthlySchedule implements PaymentSchedule{	
	public boolean isPayDate(Date date){
		return DateUtil.isLastWorkDayOfMonth(date);
	}
	
	public Date getPayPeriodStartDate( Date payPeriodEndDate){
		return DateUtil.getFirstDayOfMonth(payPeriodEndDate);
	}
}
