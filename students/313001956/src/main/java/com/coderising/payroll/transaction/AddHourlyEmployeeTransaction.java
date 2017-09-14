package com.coderising.payroll.transaction;

import com.coderising.payroll.classification.HourlyClassification;
import com.coderising.payroll.domain.PaymentClassification;
import com.coderising.payroll.domain.PaymentSchedule;
import com.coderising.payroll.schedule.WeeklySchedule;

public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction {
	private double rate;

	public AddHourlyEmployeeTransaction(String name, String address, double hourlyRate) {
		super(name, address);
		this.rate = hourlyRate;
	}

	@Override
	public PaymentSchedule getPaymentSchedule() {
		return new WeeklySchedule();
	}

	@Override
	public PaymentClassification getPaymentClassification() {
		return new HourlyClassification(rate);

	}

}
