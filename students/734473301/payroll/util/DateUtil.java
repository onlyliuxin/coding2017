package com.jyz.payroll.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static long getDaysBetween(Date d1, Date d2){
		
		return (d2.getTime() - d1.getTime())/(24*60*60*1000);		
	}
	
	public static Date parseDate(String txtDate){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");		
		try {
			return  sdf.parse(txtDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}		
	}
	public static boolean isFriday(Date d){
		 Calendar   calendar   =   Calendar.getInstance();
		 calendar.setTime(d);
		 System.out.println("今天周几："+calendar.get(Calendar.DAY_OF_WEEK));
         return calendar.get(Calendar.DAY_OF_WEEK) == 5;    
	}
	
	public static Date add(Date d, int days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
	public static boolean isLastDayOfMonth(Date d){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(d);
        return calendar.get(Calendar.DATE)==calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	public static Date getFirstDay(Date d){
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(d);
		int day = calendar.get(Calendar.DATE);
		calendar.add(Calendar.DATE, -(day-1));
		return calendar.getTime();
	}
	public static void main(String [] args) throws Exception{
		System.out.println(DateUtil.isLastDayOfMonth(DateUtil.parseDate("2017-6-29")));
		
		System.out.println(DateUtil.getFirstDay(DateUtil.parseDate("2017-6-30")));
	}
	
	public static boolean between(Date d, Date date1, Date date2){
		return d.after(date1) && d.before(date2);
	}
}
