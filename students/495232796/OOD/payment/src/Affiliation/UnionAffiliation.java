package Affiliation;

import java.util.Date;
import java.util.Map;

import PayCheck.PayCheck;
import DateUtil.DateUtil;

public class UnionAffiliation extends Affiliation{
	private String memId;
	private double weeklyDue;
	private Map<Date, ServiceCharge> serviceCharges;
	@Override
	public double calculateDeduction(PayCheck pc) {
		int fridays = DateUtil.getFridays(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());
		double totalDue = fridays*this.weeklyDue;
		
		double totalCharges = 0.0;
		for (ServiceCharge sc : serviceCharges.values()) {
			if (DateUtil.between(sc.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				totalCharges += sc.getAmount();
			}
		}
		return totalDue+totalCharges;
	}

}
