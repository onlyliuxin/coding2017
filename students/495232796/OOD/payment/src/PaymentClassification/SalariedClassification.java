package PaymentClassification;

import PayCheck.PayCheck;

public class SalariedClassification extends PaymentClassification {
	private double salary = 0.0;

	@Override
	public double calculatePay(PayCheck pc) {
		return salary;
	}
}
