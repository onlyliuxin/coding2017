package com.coderising.ood.payroll.method;

import com.coderising.ood.payroll.Paycheck;
import com.coderising.ood.payroll.db.MockDB;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class BankMethod implements PaymentMethod {

    @Override
    public void pay(Paycheck pc, int employId) {
        //pay to bank
        System.out.println("pay to bank " + employId);
        MockDB.put(employId, pc);
    }
}
