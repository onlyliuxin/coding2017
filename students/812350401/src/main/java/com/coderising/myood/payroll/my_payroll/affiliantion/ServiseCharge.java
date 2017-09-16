package com.coderising.myood.payroll.my_payroll.affiliantion;

import com.coderising.myood.payroll.my_payroll.util.DateUtil;

import java.util.Date;

/**
 * Created by thomas_young on 16/9/2017.
 */
public class ServiseCharge {
    private Date date;
    private double amount;

    public ServiseCharge(String dateStr, double amount) {
        this.date = DateUtil.parseDate(dateStr);
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
