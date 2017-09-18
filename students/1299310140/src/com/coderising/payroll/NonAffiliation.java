package com.coderising.payroll;

public class NonAffiliation implements Affiliation {

	@Override
	public double calculateDeductions(Paycheck pc) {
		
		return 0;
	}

}
