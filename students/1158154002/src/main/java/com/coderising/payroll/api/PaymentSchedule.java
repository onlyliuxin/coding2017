package com.coderising.payroll.api;

import java.util.Date;

public interface PaymentSchedule {
	public boolean isPayDate(Date date);
	public Date getPayPeriodStartDate( Date payPeriodEndDate);
}
