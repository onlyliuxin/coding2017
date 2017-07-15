package payroll.classify;

import payroll.PayCheck;

/**
 * 分类
 * Created by lx on 2017/7/8.
 */
public interface PaymentClassification {
    public double calculdatePay(PayCheck pc);
}
