package com.coderising.payroll;

import java.util.Date;

public class BiWeeklySchedule implements PaymentSchedule {
	Date firstPayableFriday = DateUtil.parseDate("2017-6-2");

	@Override
	public boolean isPayDate(Date date) {
		
		long interval = DateUtil.getDaysBetween(firstPayableFriday,date);
		return interval % 14 == 0;
	}

	@Override
	public Date getPayPeriodStartDate(Date payPeriodEndDate) {
		return DateUtil.add(payPeriodEndDate, -13);
	}

}
