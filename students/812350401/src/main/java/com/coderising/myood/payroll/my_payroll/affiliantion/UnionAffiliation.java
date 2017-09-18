package com.coderising.myood.payroll.my_payroll.affiliantion;


import com.coderising.myood.payroll.my_payroll.domain.Affiliation;
import com.coderising.myood.payroll.my_payroll.domain.Paycheck;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;

import java.util.LinkedList;
import java.util.List;

public class UnionAffiliation implements Affiliation {
	private List<ServiseCharge> serviseCharges;
	private double weeklyDue;

	public UnionAffiliation(double weeklyDue) {
		this.weeklyDue = weeklyDue;
		serviseCharges = new LinkedList<>();
	}

	@Override
	public double calculateDeductions(Paycheck pc) {
		double totalCharge = serviseCharges.stream()
				.filter(s -> DateUtil.between(s.getDate(), pc.getPayPeriodStart(), pc.getPayPeriodEnd()))
				.map(s -> s.getAmount())
                .reduce(0d, Double::sum);
        double totalDue = weeklyDue * DateUtil.dateBetween(pc.getPayPeriodStart(), pc.getPayPeriodEnd())
                .filter(d -> DateUtil.isFriday(d))
                .count();
        return totalCharge + totalDue;
	}

	public void addServiceCharge(ServiseCharge serviseCharge) {
		this.serviseCharges.add(serviseCharge);
	}

}
