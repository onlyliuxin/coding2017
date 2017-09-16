package com.coderising.myood.payroll.liuxin_payroll.affiliation;

import com.coderising.payroll.domain.Affiliation;
import com.coderising.payroll.domain.Paycheck;

public class NonAffiliation implements Affiliation{
	public double calculateDeductions(Paycheck pc){
		return 0.0;
	}
}
