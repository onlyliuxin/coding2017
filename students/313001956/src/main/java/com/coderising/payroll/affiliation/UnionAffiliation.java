package com.coderising.payroll.affiliation;

import java.util.Date;
import java.util.Map;

import com.coderising.payroll.domain.Affiliation;
import com.coderising.payroll.domain.Paycheck;
import com.coderising.payroll.domain.ServiceCharge;
import com.coderising.payroll.util.DateUtil;

public class UnionAffiliation implements Affiliation {

	private String memberId;
	private double weeklyDue;

	public UnionAffiliation(String memberId, double weeklyDue) {
		this.memberId = memberId;
		this.weeklyDue = weeklyDue;
	}

	Map<Date, ServiceCharge> charges;

	@Override
	public double calculateDeductions(Paycheck pc) {
		int fridays = DateUtil.getFridayCountBetween(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());
		double totalDue = fridays * weeklyDue;
		double totalCharge = 0;
		for (ServiceCharge charge : charges.values()) {
			if (DateUtil.between(charge.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				totalCharge += charge.getAmount();
			}
		}
		double deduction = totalDue + totalCharge;

		return deduction;
	}

}
