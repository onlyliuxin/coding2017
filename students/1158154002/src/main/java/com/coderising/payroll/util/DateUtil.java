package com.coderising.payroll.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static boolean isLasyDayOfMonth(Date date) {
		Calendar b = Calendar.getInstance();
		b.setTime(date);
		int lastDay = b.getActualMaximum(Calendar.DAY_OF_MONTH);
		int now = b.get(Calendar.DAY_OF_MONTH);
		return now == lastDay;
	}

	public static Date getFirstDay(Date payPeriodEndDate) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}

	public static Date add(Date date, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, num);
		return c.getTime();
	}

	public static Date parseDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = null;
		try {
			time = sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

	public static boolean isFriday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK) == 6;
	}

	public static int getDaysBetween(Date start, Date end) {
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(end);

		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
		aCalendar.setTime(start);

		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

		return day1 - day2;
	}

	public static boolean between(Date saleDate, Date payPeriodStartDate, Date payPeriodEndDate) {
		
		return saleDate.getTime()>=payPeriodStartDate.getTime()&&saleDate.getTime()<=payPeriodEndDate.getTime();
	}
}
