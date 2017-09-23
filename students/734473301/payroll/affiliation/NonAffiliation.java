package com.jyz.payroll.affiliation;

import com.jyz.payroll.domain.Affiliation;
import com.jyz.payroll.domain.Paycheck;

public class NonAffiliation implements Affiliation{
	public double calculateDeductions(Paycheck pc){
		return 9.0;
	}
}
