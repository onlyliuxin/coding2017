package payroll.service;

import payroll.Employee;
import payroll.PayCheck;
import java.util.List;

/**
 * Created by lx on 2017/7/8.
 */
public interface IPayrollService {
    List<Employee> getAllEmployees();

    boolean savePaycheck(PayCheck pc);
}
