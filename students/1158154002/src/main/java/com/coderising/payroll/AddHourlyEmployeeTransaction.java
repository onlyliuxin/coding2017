package com.coderising.payroll;

import com.coderising.payroll.api.AddEmployeeTransaction;
import com.coderising.payroll.api.Affiliation;
import com.coderising.payroll.api.PaymentClassification;
import com.coderising.payroll.api.PaymentMethod;
import com.coderising.payroll.api.PaymentSchedule;

public class AddHourlyEmployeeTransaction extends AddEmployeeTransaction{
	private double rate;
	public AddHourlyEmployeeTransaction(String name, String address, PaymentMethod paymentMethod,
			Affiliation affiliation) {
		super(name, address, paymentMethod, affiliation);
		this.rate=rate;
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
