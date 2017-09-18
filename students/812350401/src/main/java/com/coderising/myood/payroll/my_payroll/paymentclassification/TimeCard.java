package com.coderising.myood.payroll.my_payroll.paymentclassification;


import com.coderising.myood.payroll.my_payroll.util.DateUtil;

import java.util.Date;

/**
 * Created by thomas_young on 16/9/2017.
 */
public class TimeCard {


    private Date date;
    private int hours;

    public TimeCard(String dateStr, int hours) {
        this.date = DateUtil.parseDate(dateStr);
        this.hours = hours;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
