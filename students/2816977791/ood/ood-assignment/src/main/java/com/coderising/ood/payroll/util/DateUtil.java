package com.coderising.ood.payroll.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class DateUtil {

    public static boolean isDuring(Date start, Date end, Date date) {
        long oneDay = 24 * 60 * 60 * 1000;
        long d1 = start.getTime() / oneDay;
        long d2 = end.getTime() / oneDay;
        long d = date.getTime() / oneDay;

        if (d1 <= d && d <= d2) {
            return true;
        }

        return false;
    }


    public static int getFridayNumber(Date start, Date end) {
        Calendar starCalendar = Calendar.getInstance();
        starCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        int result = 0;
        while (starCalendar.before(endCalendar)) {

            starCalendar.add(Calendar.DATE, 1);

            if (starCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                result++;
                starCalendar.add(Calendar.DATE, 6);
            }

        }
        return result;
    }

    public static boolean isFriday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            return true;
        }
        return false;
    }

    public static boolean isLastWorkDay(Date date) {

        Calendar calToday = Calendar.getInstance();
        calToday.setTime(date);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        while (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY &&
                cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.add(Calendar.DATE, -1);
        }

        return cal.get(Calendar.DAY_OF_MONTH) == calToday.get(Calendar.DAY_OF_MONTH);
    }

    public static Date getStartDate(Date endDate, int duringDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(endDate);
        cal.add(Calendar.DATE, duringDay);
        return cal.getTime();
    }

    public static Date getStartOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
        return cal.getTime();
    }

    public static boolean equalDay(Date date1, Date date2) {
        long oneDay = 24 * 60 * 60 * 1000;
        long d1 = date1.getTime() / oneDay;
        long d2 = date2.getTime() / oneDay;
        return d1 == d2;
    }

    public static long getInterval(Date start, Date end) {
        long oneDay = 24 * 60 * 60 * 1000;
        long d1 = start.getTime() / oneDay;
        long d2 = end.getTime() / oneDay;
        return d2 - d1;
    }
}

