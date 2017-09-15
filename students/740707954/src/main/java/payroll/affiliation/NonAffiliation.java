package payroll.affiliation;

import payroll.PayCheck;

/**
 * 非会员
 * Created by lx on 2017/7/8.
 */
public class NonAffiliation implements Affiliation {
    @Override
    public double calculateDeductions(PayCheck pc) {
        return 0;
    }
}
