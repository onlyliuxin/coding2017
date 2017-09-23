package com.pojo;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:17
 *
 * 付款分类
 */
public interface PaymentClassification {

    /**
     * 计算应该支付的工资
     * @param pc
     * @return
     */
    public double calculatePay(Paycheck pc);
}
