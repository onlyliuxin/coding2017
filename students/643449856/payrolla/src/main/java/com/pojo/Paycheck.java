package com.pojo;

import java.util.Date;
import java.util.Map;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:07
 * 薪水
 */
public class Paycheck {

    private Date payPeriodStart;
    private Date payPeriodEnd;
    private double grossPay;  // 应发工资
    private double netPay; // 实付工资
    private double deductions; // 扣除工资
    private Map<String, String> itsFields;

    public Paycheck(Date payPeriodStart, Date payPeriodEnd){
        this.payPeriodStart = payPeriodStart;
        this.payPeriodEnd = payPeriodEnd;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;

    }

    public void setDeductions(double deductions) {
        this.deductions  = deductions;
    }

    public void setNetPay(double netPay){
        this.netPay = netPay;
    }

    public Date getPayPeriodEndDate() {

        return this.payPeriodEnd;
    }
    public Date getPayPeriodStartDate() {

        return this.payPeriodStart;
    }


    public Date getPayPeriodStart() {
        return payPeriodStart;
    }

    public void setPayPeriodStart(Date payPeriodStart) {
        this.payPeriodStart = payPeriodStart;
    }

    public Date getPayPeriodEnd() {
        return payPeriodEnd;
    }

    public void setPayPeriodEnd(Date payPeriodEnd) {
        this.payPeriodEnd = payPeriodEnd;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getNetPay() {
        return netPay;
    }

    public double getDeductions() {
        return deductions;
    }

    public Map<String, String> getItsFields() {
        return itsFields;
    }

    public void setItsFields(Map<String, String> itsFields) {
        this.itsFields = itsFields;
    }
}
