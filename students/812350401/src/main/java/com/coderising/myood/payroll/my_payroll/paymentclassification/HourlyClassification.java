package com.coderising.myood.payroll.my_payroll.paymentclassification;

import com.coderising.myood.payroll.my_payroll.domain.Paycheck;
import com.coderising.myood.payroll.my_payroll.domain.PaymentClassification;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thomas_young on 16/9/2017.
 * 临时工工资计算
 */
public class HourlyClassification implements PaymentClassification {
    private int MAX_HOURS = 8;
    private double EXTRA_SCALE= 1.5;
    private double hourlyRate;
    private List<TimeCard> timeCards;

    public HourlyClassification(double hourlyRate) {
        this.hourlyRate = hourlyRate;
        timeCards = new LinkedList<>();
    }

    @Override
    public double calculatePay(Paycheck pc) {
        double totalPay = timeCards.stream()
                .filter(t -> DateUtil.between(t.getDate(), pc.getPayPeriodStart(), pc.getPayPeriodEnd()))
                .map(this::calulateTimeCardPay)
                .reduce(0d, Double::sum);
        return totalPay;
    }

    public void addTimeCard(TimeCard timeCard) {
        timeCards.add(timeCard);
    }

    public List<TimeCard> getTimeCards() {
        return timeCards;
    }

    private double calulateTimeCardPay(TimeCard timeCard) {
        if (timeCard.getHours() <= MAX_HOURS) {
            return hourlyRate * timeCard.getHours();
        } else {
            return hourlyRate * MAX_HOURS + EXTRA_SCALE * hourlyRate * (timeCard.getHours() - MAX_HOURS);
        }
    }
}
