package payroll.method;

import payroll.PayCheck;

/**
 * 支付方式
 * Created by lx on 2017/7/8.
 */
public interface PaymentMethod {
    public void pay(PayCheck pc);
}
