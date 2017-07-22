package com.coderising.payroll;

import java.util.ArrayList;
import java.util.List;

public class HourlyClassification implements PaymentClassification{
	private double hourlyRate;
	private List<TimeCard> timeCards = new ArrayList<>();
	
	public HourlyClassification(double hourlyRate, List<TimeCard> timeCards) {
		super();
		this.hourlyRate = hourlyRate;
		this.timeCards = timeCards;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public double calculatePay(Paycheck pc){
		double salary = 0.0;
		for(TimeCard tc:timeCards){
			if(DateUtil.between(tc.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())){
				salary += calculatePayForTimeCard(tc);
			}
		}
		return salary;
	}
	
	private double calculatePayForTimeCard(TimeCard tc){
		if(tc.getHours() > 8){
			return 8*hourlyRate + (tc.getHours()-8)*1.5*hourlyRate;
		}
		return tc.getHours()*hourlyRate;
	}
}
