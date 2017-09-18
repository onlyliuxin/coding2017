package com.coderising.myood.payroll.my_payroll.paymentschedule;

import com.coderising.myood.payroll.my_payroll.domain.PaymentSchedule;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;

import java.util.Date;

/**
 * Created by thomas_young on 16/9/2017.
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
