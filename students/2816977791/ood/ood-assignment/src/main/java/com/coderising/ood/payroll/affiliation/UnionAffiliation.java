package com.coderising.ood.payroll.affiliation;

import com.coderising.ood.payroll.Paycheck;
import com.coderising.ood.payroll.util.DateUtil;

import java.util.List;

/**
 * @author nvarchar
 *         date 2017/7/10
 */
public class UnionAffiliation implements Affiliation{
    private int memberId;
    private double weeklyDue;
    private List<ServiceCharge> serviceChargeList;

    @Override
    public double calculateDeductions(Paycheck pc) {
        int fridays = DateUtil.getFridayNumber(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate());
        double totalDue = fridays * weeklyDue;
        double totalCharge = 0.0d;
        for (ServiceCharge serviceCharge : serviceChargeList){
            if (DateUtil.isDuring(pc.getPayPeriodStartDate(), pc.getPayPeriodEndDate(), serviceCharge.getDate())){
                totalCharge += serviceCharge.getAmount();
            }
        }
        return totalCharge + totalDue;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public double getWeeklyDue() {
        return weeklyDue;
    }

    public void setWeeklyDue(double weeklyDue) {
        this.weeklyDue = weeklyDue;
    }

    public List<ServiceCharge> getServiceChargeList() {
        return serviceChargeList;
    }

    public void setServiceChargeList(List<ServiceCharge> serviceChargeList) {
        this.serviceChargeList = serviceChargeList;
    }
}
