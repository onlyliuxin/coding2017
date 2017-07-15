package PayCheck;

import java.util.Date;

public class PayCheck {
	private int payPeriodStart;
	private int payPeriodEnd;
	private double grossPay;
	private double deductions;
	private double netPay;
	public double getGrossPay() {
		return grossPay;
	}
	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}
	public double getDeductions() {
		return deductions;
	}
	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}
	public double getNetPay() {
		return netPay;
	}
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	
	public Date getPayPeriodStartDate() {
		return new Date();
	}
	public Date getPayPeriodEndDate() {
		return new Date();
	}
}
