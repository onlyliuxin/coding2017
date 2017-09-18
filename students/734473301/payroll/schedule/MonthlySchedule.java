package com.jyz.payroll.schedule;

import java.util.Date;

import com.jyz.payroll.domain.PaymentSchedule;
import com.jyz.payroll.util.DateUtil;

/**
 * 月薪族支付
 */
public class MonthlySchedule implements PaymentSchedule {

	@Override
	public boolean isPayDate(Date date) {		
		//return DateUtil.isLastDayOfMonth(date);
		System.out.println("月薪族测试，总是每月的最后一天");
		return  true;
	}

	@Override
	public Date getPayPeriodStartDate(Date payPeriodEndDate) {		
		return DateUtil.getFirstDay(payPeriodEndDate);
	}

}
