package payroll.service;

import payroll.Employee;
import payroll.PayCheck;
import java.util.List;

/**
 * Created by lx on 2017/7/8.
 */
public class PayrollServiceImpl implements IPayrollService {

    /**
     * 获取所有员工信息
     * @return
     */
    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    /**
     * 保存paycheck
     * @param pc
     * @return
     */
    @Override
    public boolean savePaycheck(PayCheck pc) {
        System.out.println("保存。。。。。");
        return true;
    }
}
