package com.coderising.payroll;

import com.coderising.payroll.api.Affiliation;

public class NonAffiliation implements Affiliation{

	@Override
	public double calculateDeductions(Paycheck pc) {
		return 0;
	}

}
