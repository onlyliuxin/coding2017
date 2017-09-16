package com.coderising.myood.payroll.my_payroll.domain;

import java.util.Date;

public interface PaymentSchedule {
	public boolean isPayDate(Date date);
	public Date getPayPeriodStartDate(Date payPeriodEndDate);
}
