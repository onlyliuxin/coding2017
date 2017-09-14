package com.coderising.payroll.classification;

import java.util.Date;
import java.util.Map;

import javax.swing.plaf.PanelUI;

import com.coderising.payroll.domain.Paycheck;
import com.coderising.payroll.domain.PaymentClassification;
import com.coderising.payroll.domain.SalesReceipt;
import com.coderising.payroll.domain.TimeCard;
import com.coderising.payroll.util.DateUtil;

public class HourlyClassification implements PaymentClassification {
	private double rate;
	private Map<Date, TimeCard> timeCards;

	public HourlyClassification(double hourlyRate) {
		this.rate = hourlyRate;
	}

	public void addTimeCard(TimeCard tc) {
		timeCards.put(tc.getDate(), tc);
	}

	@Override
	public double calculatePay(Paycheck pc) {		
		double totalPay=0;
		for (TimeCard tc : timeCards.values()) {
			if (DateUtil.between(tc.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				totalPay += caculatePayforTimeCard(tc.getHours()) ;
			}
		}

		return totalPay;
	}

	private double caculatePayforTimeCard(int hours) {
		double pay = 0;
		if (hours > 8) {
			pay = rate * 8 + rate * 1.5 * (hours - 8);
		} else {
			pay = rate * hours;
		}

		return pay;
	}

}
