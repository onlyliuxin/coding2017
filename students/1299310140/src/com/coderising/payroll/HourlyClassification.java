package com.coderising.payroll;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HourlyClassification implements PaymentClassification {
	
	private double rate;
	private Map<Date,TimeCard> timeCards;
	
	public HourlyClassification(double rate) {
		super();
		this.rate = rate;
		timeCards = new HashMap<Date,TimeCard>();
	}
	
	public void addTimeCard(TimeCard tc){
		this.timeCards.put(tc.getDate(), tc);
	}
	
	private double calculatePayForTimeCard(TimeCard tc){
		int hours = tc.getHours();
		if(hours > 8){
			return 8 * rate + (hours - 8) * rate * 1.5;
		}else{
			return hours * rate;
		}
	}
	
	@Override
	public double calculatePay(Paycheck pc) {
		double totalPay = 0;
		for (TimeCard timeCard : timeCards.values()) {
			if (DateUtil.between(timeCard.getDate(),
					pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				totalPay += calculatePayForTimeCard(timeCard);
			}
		}
		return totalPay;
	}

}
