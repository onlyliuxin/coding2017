package com.coderising.payroll;

import com.coderising.payroll.api.AddEmployeeTransaction;
import com.coderising.payroll.api.Affiliation;
import com.coderising.payroll.api.PaymentClassification;
import com.coderising.payroll.api.PaymentMethod;
import com.coderising.payroll.api.PaymentSchedule;

public class AddSalariedEmployeeTransaction extends AddEmployeeTransaction{
	private double salary;
	public AddSalariedEmployeeTransaction(String name, String address, PaymentMethod paymentMethod,
			Affiliation affiliation) {
		super(name, address, paymentMethod, affiliation);
		this.salary=salary;
	}

	@Override
	public PaymentClassification getClassification() {
		return new SalariedClassification(salary);
	}

	@Override
	public PaymentSchedule getSchedule() {
		return new MonthlySchedule();
	}

}
