package com.coderising.myood.payroll.liuxin_payroll.schedule;

import com.coderising.myood.payroll.liuxin_payroll.domain.PaymentSchedule;
import com.coderising.myood.payroll.liuxin_payroll.util.DateUtil;

import java.util.Date;


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
