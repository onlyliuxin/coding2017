package payroll;

import java.util.Date;

/**
 * 可以检查是否重复执行
 * Created by lx on 2017/7/8.
 */
public class PayCheck {
    private Date payPeriodStart;
    private Date payPeriodEnd;
    private double grossPay;// 应付
    private double netPay;// 实付
    private double deductions;// 扣除

    public PayCheck(Date payPeriodStart, Date payPeriodEnd) {
        this.payPeriodStart = payPeriodStart;
        this.payPeriodEnd = payPeriodEnd;
    }

    public Date getPayPeriodEnd() {
        return payPeriodEnd;
    }

    public Date getPayPeriodStart() {
        return payPeriodStart;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }
}
