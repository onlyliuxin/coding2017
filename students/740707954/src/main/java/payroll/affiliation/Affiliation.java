package payroll.affiliation;

import payroll.PayCheck;

/**
 * Affiliation 会员
 * Created by lx on 2017/7/8.
 */
public interface Affiliation {

    /**
     * 计算服务费
     * @param pc
     * @return
     */
    double calculateDeductions(PayCheck pc);
}
