package com.coderising.payroll;

import java.util.ArrayList;
import java.util.List;

public class UnionAffiliation implements Affiliation {
	private String memberID = "";
	private int weeklyDue = 5;
	private List<ServiceCharge> serviceCharges = new ArrayList<ServiceCharge>();
	
	public UnionAffiliation(String memberID) {
		super();
		this.memberID = memberID;
	}
	
	public void addServiceCharge(ServiceCharge sc){
		this.serviceCharges.add(sc);
	}
	
	@Override
	public double calculateDeductions(Paycheck pc) {
		int fridays = DateUtil.fridaysNum(pc.getPayPeriodStartDate(),
				pc.getPayPeriodEndDate());
		int totalDue = fridays * this.weeklyDue;
		int totalCharge = 0;
		for (ServiceCharge sc : serviceCharges) {
			if (DateUtil.between(sc.getDate(), pc.getPayPeriodStartDate(),
					pc.getPayPeriodEndDate())) {
				totalCharge += sc.getAmount();
			}
		}
		return totalDue + totalCharge;
	}

}
