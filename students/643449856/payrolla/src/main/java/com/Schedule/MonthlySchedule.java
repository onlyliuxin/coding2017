package com.Schedule;

import com.pojo.PaymentSchedule;
import com.util.DateUtil;

import java.util.Date;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:33
 *
 * 每月的最后一天支付
 */
public class MonthlySchedule implements PaymentSchedule {


    @Override
    public boolean isPayDay(Date date) {
        return DateUtil.isLastDayOfMonth(date);
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return DateUtil.getFirstDay(payPeriodEndDate);
    }
}
