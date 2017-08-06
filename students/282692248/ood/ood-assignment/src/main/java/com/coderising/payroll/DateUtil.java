package com.coderising.payroll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	public static final long SECOND_IN_MS = 1000L;
	public static final long MINUTE_IN_S = 60L;
	public static final long HOUR_IN_MINUTE = 60L;
	public static final long DAY_IN_HOUR = 24L;
	public static final long DAY_IN_MS = DAY_IN_HOUR*HOUR_IN_MINUTE*MINUTE_IN_S*SECOND_IN_MS;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static boolean isFriday(Date date){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
	}
	
	public static int getDaysBetween(Date beginDate, Date endDate){
		assert(endDate.after(beginDate));
		return (int)((endDate.getTime() - beginDate.getTime())/DAY_IN_MS);
	}
	
	public static Date parseDate(String strDate){
		try{
			return sdf.parse(strDate);
		}
		catch(ParseException e){
			throw new RuntimeException(e);
		}
	}
	
	public static Date add(Date date, int dayCount){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dayCount);
		return calendar.getTime();
	}
	
	/** 
	 * 判断当前日期是否是当月最后一个工作日
	 * true: 当前日期是工作日并且与下一工作日不同月
	 *  */
	public static boolean isLastWorkDayOfMonth(Date d){
		if(isWeekday(d)){
			Date nextWeekday = nextWeekday(d);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(d);
			int mouth = calendar.get(Calendar.MONTH);
			calendar.setTime(nextWeekday);
			return mouth != calendar.get(Calendar.MONTH);
		}
		return false;
	}
	
	public static boolean isWeekday(Date d){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return Calendar.MONDAY <= dayOfWeek && dayOfWeek <= Calendar.FRIDAY;
	}
	
	public static Date nextWeekday(Date d){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case Calendar.FRIDAY:
			calendar.add(Calendar.DATE, 3);
			break;
		case Calendar.SATURDAY:
			calendar.add(Calendar.DATE, 2);
			break;
		default:
			calendar.add(Calendar.DATE, 1);
			break;
		}
		return calendar.getTime();
	}
	
	public static Date getFirstDayOfMonth(Date d){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	public static boolean between(Date d, Date startDate, Date endDate){
		return d.compareTo(startDate)>=0 && d.compareTo(endDate)<=0;
	}
	
	public static String format(Date d){
		return sdf.format(d);
	}
}
