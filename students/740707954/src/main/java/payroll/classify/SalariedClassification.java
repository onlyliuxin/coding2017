package payroll.classify;

import payroll.PayCheck;

/**
 * 月薪雇员
 * Created by lx on 2017/7/8.
 */
public class SalariedClassification implements PaymentClassification {
    private double salary;

    /**
     * 计算支付
     * @param pc
     * @return
     */
    @Override
    public double calculdatePay(PayCheck pc) {
        return salary;
    }
}
