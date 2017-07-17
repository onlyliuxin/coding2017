package com.coderising.ood.payroll.method;

import com.coderising.ood.payroll.Paycheck;
import com.coderising.ood.payroll.db.MockDB;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class MailMethod implements PaymentMethod {
    @Override
    public void pay(Paycheck pc, int employId) {
        //mail to employee
        System.out.println("mail to employee " + employId);
        MockDB.put(employId, pc);
    }
}
