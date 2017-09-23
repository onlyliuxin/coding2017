package com.jyz.payroll.schedule;

import java.util.Date;

import com.jyz.payroll.domain.PaymentSchedule;
import com.jyz.payroll.util.DateUtil;

/**
 * 每周五支付
 */
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
