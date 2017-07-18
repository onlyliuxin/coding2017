package com.coderising.ood.payroll.classfication;

import com.coderising.ood.payroll.Paycheck;
import com.coderising.ood.payroll.util.DateUtil;

import java.util.Date;
import java.util.Map;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class HourlyClassification implements PaymentClassification {
    private double rate;
    private Map<Date, TimeCard> timeCards;

    public void addTimeCard(TimeCard tc) {
        timeCards.put(tc.getDate(), tc);
    }

    @Override
    public double calculatePay(Paycheck pc) {
        double daysMoney = 0.0;
        for (Date date : timeCards.keySet()) {
            if (DateUtil.isDuring(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate(), date)) {
                daysMoney += calculateHours(timeCards.get(date).getHours());
            }
        }
        return daysMoney;
    }

    private double calculateHours(int hour) {
        int extendHour = hour - 8 >= 0 ? hour - 8 : 0;
        return hour * rate + extendHour * rate * 1.5;
    }
}
