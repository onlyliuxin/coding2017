package com.coderising.ood.payroll;

import com.coderising.ood.payroll.affiliation.Affiliation;
import com.coderising.ood.payroll.classfication.PaymentClassification;
import com.coderising.ood.payroll.method.PaymentMethod;
import com.coderising.ood.payroll.schedule.PaymentSchedule;

import java.util.Date;

public class Employee {
    int id;
    String name;
    String address;
    Affiliation affiliation;


    PaymentClassification classification;
    PaymentSchedule schedule;
    PaymentMethod paymentMethod;

    public Employee(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public boolean isPayDay(Date d) {
        return schedule.isPayDate(id, d);
    }

    public boolean isPayed(Date d) {
        return schedule.isPayed(id, d);
    }

    public Date getPayPeriodStartDate(Date d) {
        return schedule.getPayPeriodStartDate(d);
    }

    public void payDay(Paycheck pc) {

        if (isPayDay(new Date()) && isPayed(new Date())) {
            double grossPay = classification.calculatePay(pc);
            double deduction = affiliation.calculateDeductions(pc);
            double netPay = grossPay - deduction;
            pc.setGrossPay(grossPay);
            pc.setDeductions(deduction);
            pc.setNetPay(netPay);

            paymentMethod.pay(pc, id);
        }
    }

    public void setClassification(PaymentClassification classification) {
        this.classification = classification;
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}

