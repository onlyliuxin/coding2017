package PaymentSchedule;

import java.util.Date;

import DateUtil.DateUtil;

public class MothlySchedule implements PaymentSchedule{

	@Override
	public boolean isPayDay(Date date) {
		return DateUtil.isLastDayofMonth(date);
	}

	@Override
	public Date getPayPeriodStartDate(Date date) {
		return DateUtil.getFirstDay(date);
	}

}
