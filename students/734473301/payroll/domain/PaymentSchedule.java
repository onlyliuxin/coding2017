package com.jyz.payroll.domain;

import java.util.Date;

public interface PaymentSchedule {
	/**
	 * 今天是不是发薪水的日子
	 * @param date
	 * @return
	 */
	 boolean isPayDate(Date date);

	/**
	 * 上一次发薪水的日子
	 * @param payPeriodEndDate
	 * @return
	 */
	 Date getPayPeriodStartDate(Date payPeriodEndDate);
}
