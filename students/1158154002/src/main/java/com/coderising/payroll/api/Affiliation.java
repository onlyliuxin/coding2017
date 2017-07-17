package com.coderising.payroll.api;

import com.coderising.payroll.Paycheck;

public interface Affiliation {
	public double calculateDeductions(Paycheck pc);
}
