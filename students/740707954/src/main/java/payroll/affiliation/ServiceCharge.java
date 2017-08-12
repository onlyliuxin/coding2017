package payroll.affiliation;

import java.util.Date;

/**
 * 服务费用
 * Created by lx on 2017/7/8.
 */
public class ServiceCharge {
    private Date date;
    private double amount;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
