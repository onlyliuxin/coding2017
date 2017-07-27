package payroll.classify;

import payroll.PayCheck;
import payroll.util.DateUtil;

import java.util.Date;
import java.util.Map;

/**
 * 小时工
 * Created by lx on 2017/7/8.
 */
public class HourlyClassification implements PaymentClassification {
    private Map<Date, TimeCard> timeCards;
    private double rate;// 价格

    /**
     * 统计时间卡在pc.getStartDate 和 pc.getEndDate之间
     * 并计算薪水
     *
     * @param pc
     * @return
     */
    @Override
    public double calculdatePay(PayCheck pc) {
        double totalPay = 0.0d;
        for (Map.Entry<Date, TimeCard> entry : timeCards.entrySet()) {
            TimeCard tc = entry.getValue();
            if (DateUtil.between(tc.getDate(), pc.getPayPeriodStart(), pc.getPayPeriodEnd())) {
                totalPay += calculatePayForTimeCard(tc);
            }
        }
        return totalPay;
    }

    /**
     * 计算每个时间卡的薪水
     *
     * @param tc
     * @return
     */
    public double calculatePayForTimeCard(TimeCard tc) {
        int hours = tc.getHours();
        if (tc.getHours() > 8) {
            return 8 * rate + (hours - 8) * 1.5 * rate;
        } else {
            return 8 * rate;
        }
    }
}
