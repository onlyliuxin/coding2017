package com.coderising.ood.payroll.classfication;

import com.coderising.ood.payroll.Paycheck;
import com.coderising.ood.payroll.util.DateUtil;

import java.util.Date;
import java.util.Map;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class SalariedClassification implements PaymentClassification {
    private double salary;
    private double rate;
    private Map<Date, SalesReceipt> salesReceiptMap;


    @Override
    public double calculatePay(Paycheck pc) {
        double commission = 0.0;
        for (Date date : salesReceiptMap.keySet()) {
            if (DateUtil.isDuring(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate(), date)) {
                commission += salesReceiptMap.get(date).getAmount() * rate;
            }
        }

        return salary + commission;
    }
}
