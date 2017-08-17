package com.coderising.payroll;

import java.util.Date;

import com.coderising.payroll.api.PaymentSchedule;
import com.coderising.payroll.util.DateUtil;

public class MonthlySchedule implements PaymentSchedule{

	@Override
	public boolean isPayDate(Date date) {
		return DateUtil.isLasyDayOfMonth(date);
	}

	@Override
	public Date getPayPeriodStartDate(Date payPeriodEndDate) {
		return DateUtil.getFirstDay(payPeriodEndDate);
	}

}
