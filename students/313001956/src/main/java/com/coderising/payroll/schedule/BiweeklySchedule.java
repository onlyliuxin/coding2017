package com.coderising.payroll.schedule;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.coderising.payroll.domain.PaymentSchedule;
import com.coderising.payroll.util.DateUtil;

public class BiweeklySchedule implements PaymentSchedule {
	Date firstPayableFriday = DateUtil.parseDate("2017-6-2");

	@Override
	public boolean isPayDate(Date date) {
		long daysBetween = DateUtil.getDaysBetween(firstPayableFriday, date);

		return daysBetween % 14 == 0;
	}

	@Override
	public Date getPayPeriodStartDate(Date payPeriodEndDate) {
		return DateUtil.add(payPeriodEndDate, -13);
	}

	public static void main(String[] args) throws Exception {
		BiweeklySchedule schedule = new BiweeklySchedule();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2017-07-21");

		System.out.println(schedule.isPayDate(d));

		System.out.println(DateUtil.isFriday(d));

		System.out.println(schedule.getPayPeriodStartDate(d));
	}

}
