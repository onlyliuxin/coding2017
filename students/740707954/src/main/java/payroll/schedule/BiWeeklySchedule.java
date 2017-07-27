package payroll.schedule;

import payroll.util.DateUtil;

import java.util.Date;

/**
 * 每隔一周支付
 * Created by lx on 2017/7/8.
 */
public class BiWeeklySchedule implements PaymentSchedule {
    Date firstPayFriday = DateUtil.parse("2017-07-07");

    /**
     * 是否为支付日
     * @param date
     * @return
     */
    @Override
    public boolean isPayDate(Date date) {
//        return DateUtil.add(date, -13) == firstPayFriday;
        int interval = DateUtil.getDaysBetween(firstPayFriday, date);
        return interval % 14  == 0;
    }

    /**
     * 获取支付薪水日期的起始时间
     * @param payPeriodEndDate
     * @return
     */
    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return DateUtil.add(payPeriodEndDate, -13);
    }
}
