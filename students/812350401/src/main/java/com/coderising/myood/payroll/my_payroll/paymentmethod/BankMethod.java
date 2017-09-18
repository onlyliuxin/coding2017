package com.coderising.myood.payroll.my_payroll.paymentmethod;

import com.coderising.myood.payroll.my_payroll.domain.Paycheck;
import com.coderising.myood.payroll.my_payroll.domain.PaymentMethod;

import java.text.SimpleDateFormat;

/**
 * Created by thomas_young on 16/9/2017.
 */
public class BankMethod implements PaymentMethod {
    @Override
    public void pay(Paycheck pc) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder desp = new StringBuilder();
        desp.append("银行入账:\n")
                .append("employee_id: ").append(pc.getEmployeeId())
                .append(", 金额: ").append(pc.getNetPay())
                .append(", 区间: ").append(sdf.format(pc.getPayPeriodStart()))
                .append("~").append(sdf.format(pc.getPayPeriodEnd()));
        System.out.println(desp.toString());
    }
}
