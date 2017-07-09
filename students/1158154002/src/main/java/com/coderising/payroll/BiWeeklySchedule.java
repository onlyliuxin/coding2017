package com.coderising.payroll;

import java.util.Date;

import com.coderising.payroll.api.PaymentSchedule;
import com.coderising.payroll.util.DateUtil;

public class BiWeeklySchedule implements PaymentSchedule {
	Date firstPayableFriday = DateUtil.parseDate("2017-06-02");

	@Override
	public boolean isPayDate(Date date) {
		int interval = DateUtil.getDaysBetween(firstPayableFriday, date);
		return interval % 14 == 0;
	}

	@Override
	public Date getPayPeriodStartDate(Date payPeriodEndDate) {
		return DateUtil.add(payPeriodEndDate, -13);
	}

}
