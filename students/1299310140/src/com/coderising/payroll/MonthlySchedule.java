package com.coderising.payroll;

import java.util.Date;

public class MonthlySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date date) {
		return DateUtil.isLastDayOfMonth(date);
	}

	@Override
	public Date getPayPeriodStartDate(Date payPeriodEndDate) {
		return DateUtil.getFirstDay(payPeriodEndDate);
	}

}
