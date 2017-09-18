package com.coderising.myood.payroll.my_payroll.domain;

import java.util.Date;

public class Paycheck {
	private Date payPeriodStart;
	private Date payPeriodEnd;
	private double grossPay;
	private double netPay;
	private double deductions;
	private int employeeId;

	public Paycheck(int employeeId, Date payPeriodStart, Date payPeriodEnd){
		this.payPeriodStart = payPeriodStart;
		this.payPeriodEnd = payPeriodEnd;
		this.employeeId = employeeId;
	}

	public Date getPayPeriodStart() {
		return payPeriodStart;
	}

	public void setPayPeriodStart(Date payPeriodStart) {
		this.payPeriodStart = payPeriodStart;
	}

	public Date getPayPeriodEnd() {
		return payPeriodEnd;
	}

	public void setPayPeriodEnd(Date payPeriodEnd) {
		this.payPeriodEnd = payPeriodEnd;
	}

	public double getGrossPay() {
		return grossPay;
	}

	public double getNetPay() {
		return netPay;
	}

	public double getDeductions() {
		return deductions;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
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

	@Override
	public String toString() {
		return "Paycheck{" +
				"payPeriodStart=" + payPeriodStart +
				", payPeriodEnd=" + payPeriodEnd +
				", grossPay=" + grossPay +
				", netPay=" + netPay +
				", deductions=" + deductions +
				", employeeId=" + employeeId +
				'}';
	}
}
