package payroll.schedule;

import payroll.util.DateUtil;

import java.util.Date;

/**
 * 每月月底支付
 * Created by lx on 2017/7/8.
 */
public class MonthSchedule implements PaymentSchedule {
    /**
     * 是否为支付日
     * @param date
     * @return
     */
    @Override
    public boolean isPayDate(Date date) {
        return DateUtil.isLastDayOfMonth(date);
    }

    /**
     * 获取支付薪水日期的起始时间
     * @param payPeriodEndDate
     * @return
     */
    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return DateUtil.getFirstDayOfMonth(payPeriodEndDate);
    }
}
