package com.coderising.myood.payroll.liuxin_payroll.transaction;


import com.coderising.myood.payroll.liuxin_payroll.classification.HourlyClassification;
import com.coderising.myood.payroll.liuxin_payroll.domain.PaymentClassification;
import com.coderising.myood.payroll.liuxin_payroll.domain.PaymentSchedule;
import com.coderising.myood.payroll.liuxin_payroll.schedule.WeeklySchedule;

public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction{
	private double rate;
	AddHourlyEmployeeTransaction(String name, String address, double hourlyRate) {
		super(name, address);
		this.rate = hourlyRate;
	}
	@Override
	public PaymentClassification getClassification() {
		return new HourlyClassification(rate);
	}

	@Override
	public PaymentSchedule getSchedule() {
		
		return new WeeklySchedule();
	}	
}

