package com.coderising.ood.payroll.classfication;

import com.coderising.ood.payroll.Paycheck;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class CommissionClassification implements PaymentClassification {

    private double salary;

    @Override
    public double calculatePay(Paycheck pc) {
        return salary;
    }
}
