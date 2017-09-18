package com.coderising.myood.payroll.my_payroll.affiliantion;


import com.coderising.myood.payroll.my_payroll.domain.Affiliation;
import com.coderising.myood.payroll.my_payroll.domain.Paycheck;

public class NonAffiliation implements Affiliation {

    public NonAffiliation() {
    }

	public double calculateDeductions(Paycheck pc){
        return 0.0d;
	}

}
