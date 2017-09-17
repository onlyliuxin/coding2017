package com.coderising.myood.payroll.my_payroll.domain;

import com.coderising.myood.payroll.my_payroll.paymentclassification.*;
import com.coderising.myood.payroll.my_payroll.paymentclassification.SalesReceipt;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by thomas_young on 16/9/2017.
 */
public class PaymentClassificationTest {
    private static double TOLERANCE = 1e-5;
    @Test
    public void hourlyCalculatePay1() throws Exception {
        HourlyClassification hc = new HourlyClassification(100);
        Paycheck pc = new Paycheck(1, DateUtil.parseDate("2017-06-12"), DateUtil.parseDate("2017-07-12"));
        TimeCard t1 = new TimeCard("2017-06-11", 4);
        TimeCard t2 = new TimeCard("2017-06-15", 10);
        hc.addTimeCard(t1);
        hc.addTimeCard(t2);
        double pay = hc.calculatePay(pc);
        Assert.assertEquals(1100d, pay, TOLERANCE);
    }

    @Test
    public void hourlyCalculatePay2() throws Exception {
        PaymentClassification hc = new HourlyClassification(100);
        Paycheck pc = new Paycheck(1, DateUtil.parseDate("2017-06-12"), DateUtil.parseDate("2017-07-12"));
        com.coderising.myood.payroll.my_payroll.paymentclassification.TimeCard t1 = new com.coderising.myood.payroll.my_payroll.paymentclassification.TimeCard("2017-06-12", 4);
        com.coderising.myood.payroll.my_payroll.paymentclassification.TimeCard t2 = new com.coderising.myood.payroll.my_payroll.paymentclassification.TimeCard("2017-06-15", 10);
        ((HourlyClassification) hc).addTimeCard(t1);
        ((HourlyClassification) hc).addTimeCard(t2);
        double pay = hc.calculatePay(pc);
        Assert.assertEquals(1500d, pay, TOLERANCE);
    }

    @Test
    public void salariedCalculatePay() {
        PaymentClassification sc = new SalariedClassification(2000d);
        double pay = sc.calculatePay(null);
        Assert.assertEquals(2000d, pay, TOLERANCE);
    }

    @Test
    public void commissionCalculatePay() {
        PaymentClassification cc = new CommissionClassification(2000d, 0.1);
        Paycheck pc = new Paycheck(1, DateUtil.parseDate("2017-06-12"), DateUtil.parseDate("2017-07-12"));

        double pay = cc.calculatePay(pc);
        Assert.assertEquals(2000d, pay, TOLERANCE);

        com.coderising.myood.payroll.my_payroll.paymentclassification.SalesReceipt sr1 = new com.coderising.myood.payroll.my_payroll.paymentclassification.SalesReceipt("2017-06-13", 200d);
        com.coderising.myood.payroll.my_payroll.paymentclassification.SalesReceipt sr2 = new com.coderising.myood.payroll.my_payroll.paymentclassification.SalesReceipt("2017-06-15", 100d);
        ((CommissionClassification) cc).addSalesReceipt(sr1);
        ((CommissionClassification) cc).addSalesReceipt(sr2);
        pay = cc.calculatePay(pc);
        Assert.assertEquals(2030d, pay, TOLERANCE);

        PaymentClassification cc2 = new CommissionClassification(2000d, 0.1);
        com.coderising.myood.payroll.my_payroll.paymentclassification.SalesReceipt sr3 = new SalesReceipt("2017-06-11", 200d);
        ((CommissionClassification) cc2).addSalesReceipt(sr3);
        ((CommissionClassification) cc2).addSalesReceipt(sr1);
        pay = cc2.calculatePay(pc);
        Assert.assertEquals(2020d, pay, TOLERANCE);
    }

}