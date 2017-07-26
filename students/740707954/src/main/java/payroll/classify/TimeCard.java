package payroll.classify;

import java.util.Date;

/**
 * 时间卡
 * Created by lx on 2017/7/8.
 */
public class TimeCard {
    private int hours;// 上班时间
    private Date date;// 那天上班

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
