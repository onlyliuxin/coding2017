package com.coderising.myood.payroll.my_payroll.paymentschedule;

import com.coderising.myood.payroll.my_payroll.domain.PaymentSchedule;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;

import java.util.Date;

/**
 * Created by thomas_young on 16/9/2017.
 */
public class BiWeeklySchedule implements PaymentSchedule {
    private static Date FIRST_PAYABLE_FRIDAY = DateUtil.parseDate("2017-6-2");
    @Override
    public boolean isPayDate(Date date) {
        long interval = DateUtil.getDaysBetween(FIRST_PAYABLE_FRIDAY, date);
        return interval % 14 == 0;
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return DateUtil.add(payPeriodEndDate, -13);
    }
}
