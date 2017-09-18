package com.pojo;

import java.util.Date;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:14
 * <p>
 * 付款日期表
 */
public interface PaymentSchedule {

    /**
     * 是否是付款日
     * @param date
     * @return
     */
    public boolean isPayDay(Date date);
    public Date getPayPeriodStartDate( Date payPeriodEndDate);
}
