package com.coderising.myood.payroll.my_payroll.domain;

import com.coderising.myood.payroll.my_payroll.paymentmethod.BankMethod;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;
import org.junit.Test;


/**
 * Created by thomas_young on 16/9/2017.
 */
public class PaymentMethodTest {
    @Test
    public void testBankPay() throws Exception {
        PaymentMethod bp = new BankMethod();
        Paycheck pc = new Paycheck(1, DateUtil.parseDate("2017-06-13"), DateUtil.parseDate("2017-06-20"));
        bp.pay(pc);
    }

}