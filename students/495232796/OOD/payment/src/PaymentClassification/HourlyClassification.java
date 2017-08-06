package PaymentClassification;

import java.util.Date;
import java.util.Map;

import DateUtil.DateUtil;
import PayCheck.PayCheck;

public class HourlyClassification extends PaymentClassification{
	private double hourlyRate = 0.0;
	private Map<Date, TimeCard> timeCards;
	
	public void addTimeCard(TimeCard tc) {
		timeCards.put(tc.getDate(), tc);
	}

	@Override
	public double calculatePay(PayCheck pc) {
		double total = 0.0;
		
		for (TimeCard tc : timeCards.values()) {
			if (DateUtil.between(tc.getDate(), pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate())) {
				total += calculatePayForTimeCard(tc);
			}
		}
		
		return total;
	}
	
	private double calculatePayForTimeCard(TimeCard tc) {
		int hours = tc.getHours();
		if (hours > 8) {
			return 8*this.hourlyRate + (hours - 8)*this.hourlyRate*1.5;
		} else {
			return 8*this.hourlyRate;
		}
	}
}
