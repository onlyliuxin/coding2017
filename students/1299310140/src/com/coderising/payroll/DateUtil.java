package com.coderising.payroll;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	private static Calendar calendar = Calendar.getInstance();
	
	public static boolean between(Date date, Date startDate,
			Date endDate) {
//		boolean result = date.after(startDate) && date.before(endDate);//不包括边界
		boolean result = getDaysBetween(startDate,date) >= 0 && getDaysBetween(date,endDate) >= 0;//包括边界
		return result;
	}

	public static boolean isFriday(Date date) {
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek == 6;
	}

	public static Date add(Date date, int i) {
		calendar.setTime(date);
		calendar.add(Calendar.DATE, i);
		return calendar.getTime();
	}

	public static boolean isLastDayOfMonth(Date date) {
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		return date.equals(calendar.getTime());
	}

	public static Date getFirstDay(Date date) {
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date parseDate(String dateString){
		try
		{
		    Date date = format.parse(dateString);
		    return date;
		}
		catch (ParseException e)  
		{
		    System.out.println(e.getMessage());
		}
		return null;
	}

	public static long getDaysBetween(Date startDate, Date endDate) {
		return (endDate.getTime() - startDate.getTime()) / (1000*3600*24);
	}

	public static int fridaysNum(Date startDate, Date endDate) {
		int interval = (int) getDaysBetween(startDate,endDate);
		int fridaysNumber = interval / 7;
		
		startDate = add(startDate,fridaysNumber * 7);
		interval = (int) getDaysBetween(startDate,endDate);
		calendar.setTime(startDate);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		for(int i = 0;i <= interval;i++){
			if(dayOfWeek == 6){
				fridaysNumber++;
				break;
			}
			dayOfWeek++;
			if(dayOfWeek == 8){
				dayOfWeek = 1;
			}
		}
		return fridaysNumber;
	}
	
}
