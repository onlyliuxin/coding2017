package com.jyz.payroll.affiliation;

import com.jyz.payroll.domain.Affiliation;
import com.jyz.payroll.domain.Paycheck;

public class UnionAffiliation implements Affiliation {

	@Override
	public double calculateDeductions(Paycheck pc) {
		
		return 0;
	}

}
