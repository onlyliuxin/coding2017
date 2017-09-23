package com.Classification;

import com.pojo.Paycheck;
import com.pojo.PaymentClassification;
import com.util.DateUtil;
import com.pojo.SalesReceipt;

import java.util.Date;
import java.util.Map;

/**
 * Created by nengneng
 * Date: 2017/9/15
 * Time: 20:22
 *
 * 销售
 */
public class CommissionedClassification implements PaymentClassification {

    double salary;
    double rate;

    Map<Date, SalesReceipt> receipts;




    public CommissionedClassification(double salary , double rate){
        this.salary = salary;
        this.rate = rate;
    }



    @Override
    public double calculatePay(Paycheck pc) {
        double commission = 0.0;
        for(SalesReceipt sr : receipts.values()){
            if(DateUtil.between(sr.getSaleDate(), pc.getPayPeriodStartDate(),
                    pc.getPayPeriodEndDate())){
                commission += sr.getAmount() * rate;
            }
        }
        return salary + commission;
    }
}
