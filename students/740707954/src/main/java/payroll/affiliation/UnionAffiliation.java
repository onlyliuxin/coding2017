package payroll.affiliation;

import payroll.PayCheck;
import payroll.util.DateUtil;
import java.util.Date;
import java.util.Map;

/**
 * 会员
 * Created by lx on 2017/7/8.
 */
public class UnionAffiliation implements Affiliation {
    private int memeberId = 0;
    private double weekDue = 0;
    private Map<Date, ServiceCharge> charege;

    /**
     * 计算服务费用
     * @param pc
     * @return
     */
    @Override
    public double calculateDeductions(PayCheck pc) {
        int fridays = DateUtil.getFridaysBetween(pc.getPayPeriodStart(), pc.getPayPeriodEnd());
        double totalDue = fridays * weekDue;
        double totalCharge = 0.0d;
        for (Map.Entry<Date, ServiceCharge> entry : charege.entrySet()) {
            ServiceCharge sc = entry.getValue();
            totalCharge += sc.getAmount();
//            calculateCharge(sc);
        }
        return totalCharge + totalDue;
    }

//    private double calculateCharge(ServiceCharge sc) {
//        return sc.getAmount();
//    }


}
