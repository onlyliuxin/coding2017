package com.Classification;

import com.pojo.Paycheck;
import com.pojo.PaymentClassification;
import com.pojo.TimeCard;
import com.util.DateUtil;

import java.util.Date;
import java.util.Map;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:39
 * <p>
 * 小时工
 */
public class HourlyClassification implements PaymentClassification {


    private double rate;

    private Map<Date, TimeCard> timeCards;



    public HourlyClassification(double hourlyRate) {
        this.rate = hourlyRate;
    }

    @Override

    public double calculatePay(Paycheck pc) {

        double totalPay = 0;
        for (TimeCard t : timeCards.values()) {
            if(DateUtil.between(t.getDate(), pc.getPayPeriodStartDate(),
                    pc.getPayPeriodEndDate())){
                totalPay += calculatePayForTimeCard(t);
            }
        }
        return 0;
    }

    public void addTimeCard(TimeCard tc){
        timeCards.put(tc.getDate(), tc);
    }


    private double calculatePayForTimeCard(TimeCard  tc) {
        int hours = tc.getHours();

        if(hours > 8){
            return 8*rate + (hours-8) * rate * 1.5;
        } else{
            return 8*rate;
        }
    }
}
