package payroll.schedule;

import java.util.Date;

/**
 * Created by lx on 2017/7/8.
 */
public interface PaymentSchedule {
    public boolean isPayDate(Date date);
    public Date getPayPeriodStartDate(Date payPeriodEndDate);
}
