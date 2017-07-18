package com.coderising.payroll;

public class UnionAffiliation implements Affiliation{
	private String memberId;
	private double weeklyDue;
	
	public UnionAffiliation(double weeklyDue) {
		super();
		this.weeklyDue = weeklyDue;
	}

	public double calculateDeductions(Paycheck pc){
		//简单实现
		int dayCount = DateUtil.getDaysBetween(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());
		return (dayCount+1)/7 * weeklyDue;
	}
}
