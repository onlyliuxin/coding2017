package PaymentSchedule;

import java.util.Date;
import DateUtil.DateUtil;

public class BiWeeklySchedule implements PaymentSchedule{
	Date firstFriday = DateUtil.parseDay("2017-01-01");
	@Override
	public boolean isPayDay(Date date) {
		long interval = DateUtil.getDaysBetween(firstFriday, date);
		return interval%14 == 0;
	}

	@Override
	public Date getPayPeriodStartDate(Date date) {
		return DateUtil.add(date, -13);
	}

}
