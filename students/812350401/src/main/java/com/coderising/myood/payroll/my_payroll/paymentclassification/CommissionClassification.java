package com.coderising.myood.payroll.my_payroll.paymentclassification;

import com.coderising.myood.payroll.my_payroll.domain.Paycheck;
import com.coderising.myood.payroll.my_payroll.domain.PaymentClassification;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;
import com.sun.istack.internal.NotNull;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by thomas_young on 16/9/2017.
 * 销售计算工资
 */
public class CommissionClassification implements PaymentClassification {
    private double baseSalary;
    private double commissionRate;
    private List<SalesReceipt> salesReceipts;

    public CommissionClassification(double baseSalary, double commissionRate) {
        this.baseSalary = baseSalary;
        this.commissionRate = commissionRate;
        salesReceipts = new LinkedList<>();
    }

    @Override
    public double calculatePay(Paycheck pc) {
        if (pc == null) return baseSalary;
        return salesReceipts.stream()
                .filter(s -> DateUtil.between(s.getSaleDate(), pc.getPayPeriodStart(), pc.getPayPeriodEnd()))
                .map(this::calculateSalePay)
                .reduce(baseSalary, Double::sum);
    }

    public void addSalesReceipt(SalesReceipt salesReceipt) {
        this.salesReceipts.add(salesReceipt);
    }

    private double calculateSalePay(SalesReceipt salesReceipt) {
        return salesReceipt.getAmount() * commissionRate;
    }
}
