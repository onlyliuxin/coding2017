package com.coderising.ood.payroll.schedule;

import com.coderising.ood.payroll.util.DateUtil;

import java.util.Date;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class WeeklyPaymentSchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(int employId,Date date) {
        return DateUtil.isFriday(date);
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return DateUtil.getStartDate(payPeriodEndDate, -6);
    }
}
