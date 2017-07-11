package PaymentSchedule;

import java.util.Date;
import DateUtil.DateUtil;

public class WeeklySchedule implements PaymentSchedule{

	@Override
	public boolean isPayDay(Date date) {
		return DateUtil.isFriday(date);
	}

	@Override
	public Date getPayPeriodStartDate(Date date) {
		return DateUtil.add(date, -6);
	}

}
