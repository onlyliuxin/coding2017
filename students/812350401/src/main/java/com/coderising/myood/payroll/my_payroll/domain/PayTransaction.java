package com.coderising.myood.payroll.my_payroll.domain;

import com.coderising.myood.payroll.my_payroll.PayrollService;

import java.util.Date;
import java.util.List;

/**
 * Created by thomas_young on 17/9/2017.
 * 这是一个定时任务
 */
public class PayTransaction {
    private Date date;
    private PayrollService payrollService;

    public void execute(){
        List<Employee> employees = payrollService.getAllEmployees();
        for(Employee e : employees){
            if(e.isPayDay(date)){

                Paycheck pc = new Paycheck(e.getId(), e.getPayPeriodStart(date), date);
                if (!payrollService.checkRepeatedPay(pc)) {
                    continue;
                }
                e.payDay(pc);
                payrollService.savePaycheck(pc);
            }
        }
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPayrollService(PayrollService payrollService) {
        this.payrollService = payrollService;
    }
}
