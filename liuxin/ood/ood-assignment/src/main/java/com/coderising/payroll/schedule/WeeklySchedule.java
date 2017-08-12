package com.coderising.payroll.schedule;

import java.util.Date;

import com.coderising.payroll.domain.PaymentSchedule;
import com.coderising.payroll.util.DateUtil;

public class WeeklySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date date) {		
		return DateUtil.isFriday(date);
	}
	@Override
	public Date getPayPeriodStartDate(Date payPeriodEndDate) {		
		return DateUtil.add(payPeriodEndDate, -6);
	}

}
