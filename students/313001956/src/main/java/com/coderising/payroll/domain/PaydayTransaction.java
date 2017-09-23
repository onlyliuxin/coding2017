package com.coderising.payroll.domain;

import java.util.Date;
import java.util.List;

import com.coderising.payroll.PayrollService;

public class PaydayTransaction {
	private Date date;
	private PayrollService payrollService;

	public void execute() {
		List<Employee> employees = payrollService.getAllEmployees();
		for (Employee e : employees) {
			if (e.isPayDay(date)) {
				Paycheck pc = new Paycheck(e.getPayPeriodStartDate(date), date);
				e.payDay(pc);
				payrollService.savePaycheck(pc);
			}

		}
	}
}
