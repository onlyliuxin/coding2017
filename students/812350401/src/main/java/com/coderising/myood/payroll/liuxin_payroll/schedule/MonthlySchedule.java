package com.coderising.myood.payroll.liuxin_payroll.schedule;

import com.coderising.myood.payroll.liuxin_payroll.domain.PaymentSchedule;
import com.coderising.myood.payroll.liuxin_payroll.util.DateUtil;

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
