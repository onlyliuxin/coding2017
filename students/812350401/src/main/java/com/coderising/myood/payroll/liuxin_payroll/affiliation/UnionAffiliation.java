package com.coderising.myood.payroll.liuxin_payroll.affiliation;


import com.coderising.myood.payroll.liuxin_payroll.domain.Affiliation;
import com.coderising.myood.payroll.liuxin_payroll.domain.Paycheck;

public class UnionAffiliation implements Affiliation {

	@Override
	public double calculateDeductions(Paycheck pc) {
		
		return 0;
	}

}
