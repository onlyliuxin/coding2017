package payroll;

import payroll.affiliation.Affiliation;
import payroll.classify.PaymentClassification;
import payroll.method.PaymentMethod;
import payroll.schedule.PaymentSchedule;

import java.util.Date;

/**
 * 员工
 * Created by lx on 2017/7/8.
 */
public class Employee {
    private String id;
    private String name;
    private String address;
    private Affiliation affiliation;

    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod paymentMethod;

    public Employee(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * 计算员工薪水
     * @param pc
     */
    public void calculatePay(PayCheck pc) {
        double grossPay = classification.calculdatePay(pc);
        double deductions = affiliation.calculateDeductions(pc);
        double netPay = grossPay - deductions;
        pc.setGrossPay(grossPay);
        pc.setDeductions(deductions);
        pc.setNetPay(netPay);
        paymentMethod.pay(pc);
    }

    /**
     * 是否为支付日
     * @param d
     * @return
     */
    public boolean isPayDay(Date d) {
        return schedule.isPayDate(d);
    }

    /**
     * 获取支付的起始时间
     * @param d
     * @return
     */
    public Date getPayPeriodStartDate(Date d) {
        return schedule.getPayPeriodStartDate(d);
    }

    public void setClassifcation(PaymentClassification classifcation) {
        this.classification = classifcation;
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
