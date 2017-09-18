package com.coderising.myood.payroll.my_payroll.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Stream;

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

	public static String toDateStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

	public static boolean isFriday(Date d){
		 Calendar calendar   =   Calendar.getInstance();
		 calendar.setTime(d);
         return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
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

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 闭区间[d1, d2]
     * @param d1
     * @param d2
     * @return
     */
    public static Stream<Date> dateBetween(Date d1, Date d2) {
	    DateRange dr = new DateRange(asLocalDate(d1), asLocalDate(d2));
        return dr.stream().map(ld -> asDate(ld));
    }

    public static class DateRange implements Iterable<LocalDate> {

        private final LocalDate startDate;
        private final LocalDate endDate;

        public DateRange(LocalDate startDate, LocalDate endDate) {
            //check that range is valid (null, start < end)
            this.startDate = startDate;
            this.endDate = endDate;
        }

        @Override
        public Iterator<LocalDate> iterator() {
            return stream().iterator();
        }

        public Stream<LocalDate> stream() {
            return Stream.iterate(startDate, d -> d.plusDays(1))
                    .limit(ChronoUnit.DAYS.between(startDate, endDate) + 1);
        }

        public List<LocalDate> toList() { //could also be built from the stream() method
            List<LocalDate> dates = new ArrayList<>();
            for (LocalDate d = startDate; !d.isAfter(endDate); d = d.plusDays(1)) {
                dates.add(d);
            }
            return dates;
        }
    }

    public static boolean between(Date d, Date date1, Date date2){
        return (d.after(date1) && d.before(date2)) || d.equals(date1) || d.equals(date2);
    }

    public static void main(String [] args) throws Exception{
		System.out.println(DateUtil.isLastDayOfMonth(DateUtil.parseDate("2017-6-29")));
		
		System.out.println(DateUtil.getFirstDay(DateUtil.parseDate("2017-6-30")));
        System.out.println("**************");
        dateBetween(parseDate("2017-06-02"), parseDate("2017-06-05")).forEach(System.out::println);
	}
	

}
