package com.coderising.ood.payroll.affiliation;

import com.coderising.ood.payroll.Paycheck;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class NonAffiliation implements Affiliation {

    @Override
    public double calculateDeductions(Paycheck pc) {
        return 0;
    }
}
