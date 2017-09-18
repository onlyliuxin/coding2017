package com.Schedule;

import com.pojo.PaymentSchedule;
import com.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:29
 *
 * 每隔一周支付
 */
public class BiweeklySchedule implements PaymentSchedule {


    /**
     * 上一次支付的日期
     */
    Date firstPayableFriday = DateUtil.parseDate("2017-6-2");


    @Override
    public boolean isPayDay(Date date) {
        long interval = DateUtil.getDaysBetween(firstPayableFriday, date);
        return interval % 14 == 0;
    }

    @Override
    public Date getPayPeriodStartDate(Date payPeriodEndDate) {
        return DateUtil.add(payPeriodEndDate, -13);
    }


    public static void main(String [] args) throws Exception{
        BiweeklySchedule schedule = new BiweeklySchedule();

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse("2017-06-30");

        System.out.println(schedule.isPayDay(d));

        System.out.println(DateUtil.isFriday(d));

        System.out.println(schedule.getPayPeriodStartDate(d));
    }
}
