package com.Schedule;

import com.pojo.PaymentSchedule;
import com.util.DateUtil;

import java.util.Date;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:35
 *
 * 每周五支付
 */
public class WeeklySchedule implements PaymentSchedule {


    @Override
    public boolean isPayDay(Date date) {
        return DateUtil.isFriday(date);
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return DateUtil.add(payPeriodEndDate, -6);
    }
}
