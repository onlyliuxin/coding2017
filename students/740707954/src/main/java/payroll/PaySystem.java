package payroll;

import payroll.service.IPayrollService;
import payroll.service.PayrollServiceImpl;
import java.util.Date;
import java.util.List;

/**
 * 薪水支付系统
 * Created by lx on 2017/7/8.
 */
public class PaySystem {
    private static IPayrollService payrollService;

    static {
        payrollService = new PayrollServiceImpl();
    }

    public static void main(String[] args) {
        Date date = new Date();
        List<Employee> employees = payrollService.getAllEmployees();
        for (Employee e : employees) {
            if (e.isPayDay(date)) {
                PayCheck pc = new PayCheck(e.getPayPeriodStartDate(date), date);
                e.calculatePay(pc);
                payrollService.savePaycheck(pc);
            }
        }
    }
}
