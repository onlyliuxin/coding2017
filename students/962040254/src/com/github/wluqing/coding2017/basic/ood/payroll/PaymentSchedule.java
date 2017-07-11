package com.github.wluqing.coding2017.basic.ood.payroll;

import java.util.Date;

public interface PaymentSchedule {
	public boolean isPayDate(Date date);
	public Date getPayPeriodStartDate( Date payPeriodEndDate);
}
