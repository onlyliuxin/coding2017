package com.coderising.myood.payroll.my_payroll.domain;

import com.coderising.myood.payroll.my_payroll.paymentschedule.BiWeeklySchedule;
import com.coderising.myood.payroll.my_payroll.paymentschedule.MonthlySchedule;
import com.coderising.myood.payroll.my_payroll.paymentschedule.WeeklySchedule;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by thomas_young on 16/9/2017.
 */
public class PaymentScheduleTest {
    @Test
    public void testMonthlySchedule() throws Exception {
        PaymentSchedule ms = new MonthlySchedule();
        Assert.assertTrue(ms.isPayDate(DateUtil.parseDate("2017-06-30")));
        Assert.assertTrue(!ms.isPayDate(DateUtil.parseDate("2017-06-29")));
        Assert.assertEquals(DateUtil.parseDate("2017-06-01"),
                ms.getPayPeriodStartDate(ms.getPayPeriodStartDate(DateUtil.parseDate("2017-06-15"))));
    }

    @Test
    public void testBiWeeklySchedule() {
        PaymentSchedule bs = new BiWeeklySchedule();
        Assert.assertTrue(bs.isPayDate(DateUtil.parseDate("2017-06-16")));
        Assert.assertTrue(!bs.isPayDate(DateUtil.parseDate("2017-06-09")));
        Assert.assertEquals(DateUtil.parseDate("2017-06-03"),
                bs.getPayPeriodStartDate(DateUtil.parseDate("2017-06-16")));
    }

    @Test
    public void testWeeklySchedule() {
        PaymentSchedule ps = new WeeklySchedule();
        Assert.assertTrue(ps.isPayDate(DateUtil.parseDate("2017-06-16")));
        Assert.assertTrue(ps.isPayDate(DateUtil.parseDate("2017-06-09")));
        Assert.assertTrue(!ps.isPayDate(DateUtil.parseDate("2017-06-17")));
        Assert.assertEquals(DateUtil.parseDate("2017-06-10"),
                ps.getPayPeriodStartDate(DateUtil.parseDate("2017-06-16")));

    }
}