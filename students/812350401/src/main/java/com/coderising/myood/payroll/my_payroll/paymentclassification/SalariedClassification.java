package com.coderising.myood.payroll.my_payroll.paymentclassification;

import com.coderising.myood.payroll.my_payroll.domain.Paycheck;
import com.coderising.myood.payroll.my_payroll.domain.PaymentClassification;

/**
 * Created by thomas_young on 16/9/2017.
 * 雇员工资计算
 */
public class SalariedClassification implements PaymentClassification {
    private double salary;  // 月工资

    public SalariedClassification(double salary) {
        this.salary = salary;
    }

    @Override
    public double calculatePay(Paycheck pc) {
        return salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
