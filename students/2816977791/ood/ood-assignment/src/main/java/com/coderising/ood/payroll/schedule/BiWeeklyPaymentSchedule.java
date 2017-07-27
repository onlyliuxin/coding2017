package com.coderising.ood.payroll.schedule;

import com.coderising.ood.payroll.db.MockDB;
import com.coderising.ood.payroll.util.DateUtil;

import java.util.Date;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class BiWeeklyPaymentSchedule implements PaymentSchedule {

    @Override
    public boolean isPayDate(int employedId, Date date) {
        Date payedDate = MockDB.peek(employedId).getPayPeriodEndDate();
        if (payedDate == null) {
            return DateUtil.isFriday(date);
        } else {
            return DateUtil.getInterval(payedDate, date) % 14 == 0;
        }
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return DateUtil.getStartDate(payPeriodEndDate, -13);
    }
}
