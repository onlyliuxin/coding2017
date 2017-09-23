package com.jyz.payroll.classification;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jyz.payroll.domain.Paycheck;
import com.jyz.payroll.domain.PaymentClassification;
import com.jyz.payroll.domain.SalesReceipt;
import com.jyz.payroll.util.DateUtil;

/**
 * 销售人员计算薪水
 */
public class CommissionedClassification implements PaymentClassification {
	double salary;
	double rate;
	public CommissionedClassification(double salary , double rate){
		this.salary = salary;
		this.rate = rate;
		receipts = new HashMap<>();
		SalesReceipt sr = new SalesReceipt(new Date(),50000);
		receipts.put(new Date(),sr);
	}
	Map<Date, SalesReceipt> receipts;

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
