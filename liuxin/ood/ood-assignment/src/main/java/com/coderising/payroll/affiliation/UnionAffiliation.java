package com.coderising.payroll.affiliation;

import com.coderising.payroll.domain.Affiliation;
import com.coderising.payroll.domain.Paycheck;

public class UnionAffiliation implements Affiliation {

	@Override
	public double calculateDeductions(Paycheck pc) {
		
		return 0;
	}

}
