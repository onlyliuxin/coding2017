package PaymentClassification;

import java.util.Date;
import java.util.Map;

import PayCheck.PayCheck;
import DateUtil.DateUtil;

public class CommissionClassification extends PaymentClassification{
	private double rate = 0.0;
	private double salary = 0.0;
	private Map<Date, SalesReceipt> receipts;
	@Override
	public double calculatePay(PayCheck pc) {
		double commission = 0.0;
		for (SalesReceipt sr : receipts.values()) {
			if (DateUtil.between(sr.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				commission += sr.getAmount()*rate;
			}
		}
		return commission+salary;
	}
}
