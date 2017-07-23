package com.coderising.payroll;

import java.util.Date;

public class Paycheck {
	private Date payPeriodStart;
	private Date payPeriodEnd;
	private double grossPay;
	private double netPay;
	private double deductions;
	
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
		return "[开始时间=" + DateUtil.format(payPeriodStart) + ", 结束时间=" + DateUtil.format(payPeriodEnd) + ", 应发="
				+ grossPay + ", 扣款=" + deductions + ", 实发=" + netPay + "]";
	}
	
	
}
