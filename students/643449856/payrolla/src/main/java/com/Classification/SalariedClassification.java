package com.Classification;

import com.pojo.Paycheck;
import com.pojo.PaymentClassification;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:57
 *
 * 雇佣工
 */
public class SalariedClassification implements PaymentClassification {

    private double salary;
    public SalariedClassification(double salary){
        this.salary = salary;
    }


    @Override
    public double calculatePay(Paycheck pc) {
        return salary;
    }
}
