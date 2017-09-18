package com.jyz.payroll.domain;

import java.util.Date;
import java.util.Map;

public class Paycheck {
	private Date payPeriodStart;// 上一次支付时间
	private Date payPeriodEnd;// 这一次支付时间
	private double grossPay; //总共薪水
	private double netPay; //实际支付
	private double deductions; //应该扣除的钱
	private Map<String, String> itsFields;

	public Paycheck(Date payPeriodStart, Date payPeriodEnd){
		this.payPeriodStart = payPeriodStart;
		this.payPeriodEnd = payPeriodEnd;
	}

	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
		
	}

	public void setDeductions(double deductions) {
		this.deductions  = deductions;		
	}
	public void setNetPay(double netPay){
		this.netPay = netPay;
	}

	public Date getPayPeriodEndDate() {
		
		return this.payPeriodEnd;
	}
	public Date getPayPeriodStartDate() {
		
		return this.payPeriodStart;
	}

	@Override
	public String toString() {
		return "{" +
				"总薪水= " + grossPay +
				", 实际支付= "  + netPay +
				", 扣除部分= " + deductions +
				'}';
	}
}
