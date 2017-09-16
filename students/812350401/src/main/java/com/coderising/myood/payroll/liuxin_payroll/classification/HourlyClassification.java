package com.coderising.myood.payroll.liuxin_payroll.classification;


import com.coderising.myood.payroll.liuxin_payroll.domain.Paycheck;
import com.coderising.myood.payroll.liuxin_payroll.domain.PaymentClassification;
import com.coderising.myood.payroll.liuxin_payroll.domain.TimeCard;
import com.coderising.myood.payroll.liuxin_payroll.util.DateUtil;

import java.util.Date;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {
	private double rate;
	private Map<Date, TimeCard> timeCards;
	
	public HourlyClassification(double hourlyRate) {
		this.rate = hourlyRate;
	}
	public void addTimeCard(TimeCard tc){
		timeCards.put(tc.getDate(), tc);
	}
	@Override
	public double calculatePay(Paycheck pc) {
		double totalPay = 0;
		for(TimeCard tc : timeCards.values()){
			if(DateUtil.between(tc.getDate(), pc.getPayPeriodStartDate(),
					pc.getPayPeriodEndDate())){
				totalPay += calculatePayForTimeCard(tc);
			}
		}		
		return totalPay;
		
	}
	private double calculatePayForTimeCard(TimeCard  tc) {
	    int hours = tc.getHours();
	    
	    if(hours > 8){
	    	return 8*rate + (hours-8) * rate * 1.5;
	    } else{
	    	return 8*rate;
	    }
	}
}

