package com.coderising.ood.payroll.affiliation;

import java.util.Date;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class ServiceCharge {
    private Date date;
    private double amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
