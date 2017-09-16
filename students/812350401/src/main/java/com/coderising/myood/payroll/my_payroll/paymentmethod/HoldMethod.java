package com.coderising.myood.payroll.my_payroll.paymentmethod;

import com.coderising.myood.payroll.my_payroll.domain.Paycheck;
import com.coderising.myood.payroll.my_payroll.domain.PaymentMethod;

/**
 * Created by thomas_young on 16/9/2017.
 * 把钱发到财务那里，所示支取
 */
public class HoldMethod implements PaymentMethod {
    @Override
    public void pay(Paycheck pc) {
        StringBuilder desp = new StringBuilder();
        desp.append("财务入账:\n")
                .append("employee_id: ").append(pc.getEmployeeId())
                .append(", 金额: ").append(pc.getNetPay())
                .append(", 区间: ").append(pc.getPayPeriodStart())
                .append("~").append(pc.getPayPeriodEnd());
        System.out.println(desp.toString());
    }
}
