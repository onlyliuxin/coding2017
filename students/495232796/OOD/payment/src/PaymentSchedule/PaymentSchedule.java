package PaymentSchedule;

import java.util.Date;

public interface PaymentSchedule {

	public boolean isPayDay(Date date);
	
	public Date getPayPeriodStartDate(Date date);
}
