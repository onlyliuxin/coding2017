package com.coderising.payroll.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static long getDaysBetween(Date d1, Date d2) {

		return (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24);
	}

	public static Date parseDate(String txtDate)  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(txtDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}	

	}

	public static boolean isFriday(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
	}

	public static Date add(Date d, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	public static boolean isLastDayOfMonth(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d).equals(sdf.format(calendar.getTime()));
	}

	public static Date getFirstDay(Date d) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(DateUtil.isLastDayOfMonth(DateUtil.parseDate("2017-5-30")));

		System.out.println(DateUtil.getFirstDay(DateUtil.parseDate("2017-6-30")));
		System.out.println(DateUtil.isFriday(DateUtil.parseDate("2017-7-28")));
	}

	public static boolean between(Date d, Date date1, Date date2) {
		return d.after(date1) && d.before(date2);
	}

	public static int getFridayCountBetween(Date date1, Date date2) {
		return (int) (getDaysBetween(date1, date2) / 7);
	}
}
