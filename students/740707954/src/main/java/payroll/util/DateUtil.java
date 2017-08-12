package payroll.util;

import java.util.Date;

/**
 * 日期工具类
 * Created by lx on 2017/7/8.
 */
public class DateUtil {
    /**
     * 是否为周五
     * @param date
     * @return
     */
    public static boolean isFriday(Date date) {
        return false;
    }

    /**
     * 返回对指定日期+i的日期
     * @param payPeriodEndDate
     * @param i
     * @return
     */
    public static Date add(Date payPeriodEndDate, int i) {
        return null;
    }

    /**
     * 是否为本月的最后一天
     * @param date
     * @return
     */
    public static boolean isLastDayOfMonth(Date date) {
        return false;
    }

    /**
     * 获取本月的第一天
     * @param payPeriodEndDate
     * @return
     */
    public static Date getFirstDayOfMonth(Date payPeriodEndDate) {
        return null;
    }

    /**
     * 将字符串转换为日期
     * @param s
     * @return
     */
    public static Date parse(String s) {
        return null;
    }

    /**
     * 计算两个日期之间相差的天数
     * @param firstPayFriday
     * @param date
     * @return
     */
    public static int getDaysBetween(Date firstPayFriday, Date date) {
        return 0;
    }

    public static boolean between(Date date, Date payPeriodStart, Date payPeriodEnd) {

        return false;
    }

    /**
     * 获取两个时间段中有几个周五
     * @param payPeriodStart
     * @param payPeriodEnd
     * @return
     */
    public static int getFridaysBetween(Date payPeriodStart, Date payPeriodEnd) {
        return 0;
    }
}
