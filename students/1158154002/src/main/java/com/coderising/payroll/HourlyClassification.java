package com.coderising.payroll;

import java.util.Date;
import java.util.Map;

import com.coderising.payroll.api.PaymentClassification;
import com.coderising.payroll.util.DateUtil;

public class HourlyClassification implements PaymentClassification {

	private double rate;
	private Map<Date, TimeCard> timeCards;

	public HourlyClassification(double rate) {
		this.rate=rate;
	}
	
	public void addTimeCard(TimeCard tc) {
		timeCards.put(tc.getDate(), tc);
	}

	@Override
	public double calculatePay(Paycheck pc) {
		double totalPay = 0;
		for (TimeCard tc : timeCards.values()) {
			if (DateUtil.between(tc.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				totalPay+=calculatePayForTimeCard(tc);	
			}
		}
		return totalPay;
	}

	private double calculatePayForTimeCard(TimeCard tc) {
		int hours = tc.getHours();
		if (hours > 8) {
			return 8 * rate + (hours - 8) * 1.5 * rate;
		} else {
			return 8 * rate;
		}
	}

}
