package payroll.classify;

import payroll.PayCheck;
import payroll.util.DateUtil;

import java.util.Date;
import java.util.Map;

/**
 * 佣金雇员
 * Created by lx on 2017/7/8.
 */
public class CommissionClassification implements PaymentClassification {
    private Map<Date, SalesReceipt> salesReceipt;// 销售凭条
    private double salary;//薪水
    private double rate;//单价

    /**
     * 计算薪水
     * @param pc
     * @return
     */
    @Override
    public double calculdatePay(PayCheck pc) {
        //1 统计销售凭条在pc.getStartDate 和 pc.getEndDate之间
        //2 加上基本工资，计算薪水
        double commission = 0.0d;
        for (Map.Entry<Date, SalesReceipt> entry : salesReceipt.entrySet()) {
            SalesReceipt receipt = entry.getValue();
            if (DateUtil.between(receipt.getDate(), pc.getPayPeriodStart(), pc.getPayPeriodEnd())) {
                commission = receipt.getAmount() * rate;
            }
        }
        return commission + salary;
    }
}
