package com.pojo;

import java.util.Date;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 17:22
 */
public class Employee {

    private String id;
    private String name;
    private String address;
    private Affiliation affiliation;


    private PaymentClassification classification;
    private PaymentSchedule schedule;
    private PaymentMethod paymentMethod;

    public Employee(String name, String address){
        this.name = name;
        this.address = address;
    }


    /**
     * 是不是支付日
     * @param d
     * @return
     */
    public boolean isPayDay(Date d) {
        return this.schedule.isPayDay(d);
    }


    /**
     * 得到支付开始的日期
     * @param d
     * @return
     */
    public Date getPayPeriodStartDate(Date d) {
        return this.schedule.getPayPeriodStartDate(d);
    }


    /**
     * 计算工资
     * @param pc
     */
    public void payDay(Paycheck pc){
        double grossPay = classification.calculatePay(pc);
        double deductions = affiliation.calculateDeductions(pc);
        double netPay = grossPay - deductions;
        pc.setGrossPay(grossPay);
        pc.setDeductions(deductions);
        pc.setNetPay(netPay);
        paymentMethod.pay(pc);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public PaymentClassification getClassification() {
        return classification;
    }

    public void setClassification(PaymentClassification classification) {
        this.classification = classification;
    }

    public PaymentSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
