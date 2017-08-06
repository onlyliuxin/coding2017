package Affiliation;

import PayCheck.PayCheck;

public class NonAffiliation extends Affiliation{

	@Override
	public double calculateDeduction(PayCheck pc) {
		return 0.0;
	}

}
