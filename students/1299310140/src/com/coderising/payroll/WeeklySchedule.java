package com.coderising.payroll;

import java.util.Date;

public class WeeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date date) {
		return DateUtil.isFriday(date);
	}

	@Override
	public Date getPayPeriodStartDate(Date payPeriodEndDate) {
		return DateUtil.add(payPeriodEndDate,-6);
	}

}
