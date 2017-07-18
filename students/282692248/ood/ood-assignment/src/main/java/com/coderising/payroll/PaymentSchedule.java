package com.coderising.payroll;

import java.util.Date;

public interface PaymentSchedule {
	public boolean isPayDate(Date date);
	public Date getPayPeriodStartDate( Date payPeriodEndDate);
}
