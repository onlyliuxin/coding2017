package com.coderising.ood.payroll.schedule;

import com.coderising.ood.payroll.db.MockDB;
import com.coderising.ood.payroll.util.DateUtil;

import java.util.Date;

public interface PaymentSchedule {
	public boolean isPayDate(int employId, Date date);
	public Date getPayPeriodStartDate(Date payPeriodEndDate);

	default public boolean isPayed(int employedId, Date date){
		return DateUtil.equalDay(MockDB.peek(employedId).getPayPeriodEndDate(), date);
	}
}
