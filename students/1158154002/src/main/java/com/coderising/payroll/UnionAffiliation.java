package com.coderising.payroll;

import java.util.Date;
import java.util.Map;

import com.coderising.payroll.api.Affiliation;
import com.coderising.payroll.util.DateUtil;

public class UnionAffiliation implements Affiliation{
	private String memberID;
	private double weeklyDue;
	private Map<Date, ServiceCharge> serviceCharges;
	
	public void addServiceCharge(ServiceCharge sc){
		serviceCharges.put(sc.getDate(), sc);
	}
	
	@Override
	public double calculateDeductions(Paycheck pc) {
		double totalPay = 0;
		for (ServiceCharge sc : serviceCharges.values()) {
			if (DateUtil.between(sc.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				totalPay+=sc.getAmount();	
			}
		}
		return totalPay+calculatePayForWeeklyDue(pc);
	}

	private double calculatePayForWeeklyDue(Paycheck pc) {
		int interval=DateUtil.getDaysBetween( pc.getPayPeriodStartDate(),pc.getPayPeriodEndDate());
		return interval/7*weeklyDue;
	}


}
