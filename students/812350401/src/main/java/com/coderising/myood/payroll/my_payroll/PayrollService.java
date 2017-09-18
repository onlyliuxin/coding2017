package com.coderising.myood.payroll.my_payroll;


import com.coderising.myood.payroll.my_payroll.affiliantion.NonAffiliation;
import com.coderising.myood.payroll.my_payroll.affiliantion.UnionAffiliation;
import com.coderising.myood.payroll.my_payroll.database.EmployeeTable;
import com.coderising.myood.payroll.my_payroll.database.PaycheckTable;
import com.coderising.myood.payroll.my_payroll.domain.*;
import com.coderising.myood.payroll.my_payroll.paymentclassification.CommissionClassification;
import com.coderising.myood.payroll.my_payroll.paymentclassification.HourlyClassification;
import com.coderising.myood.payroll.my_payroll.paymentclassification.SalariedClassification;
import com.coderising.myood.payroll.my_payroll.paymentmethod.BankMethod;
import com.coderising.myood.payroll.my_payroll.paymentmethod.HoldMethod;
import com.coderising.myood.payroll.my_payroll.paymentmethod.PostOfficeMethod;
import com.coderising.myood.payroll.my_payroll.paymentschedule.BiWeeklySchedule;
import com.coderising.myood.payroll.my_payroll.paymentschedule.MonthlySchedule;
import com.coderising.myood.payroll.my_payroll.paymentschedule.WeeklySchedule;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;
import com.coderising.myood.payroll.my_payroll.util.PayrollException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class PayrollService {
    private EmployeeTable employeeTable;
    private PaycheckTable paycheckTable;

    public void savePaycheck(Paycheck pc) {
        PaycheckTable.PaycheckBuilder pb = new PaycheckTable.PaycheckBuilder();
        pb.deductions(pc.getDeductions())
                .employee_id(pc.getEmployeeId())
                .gross_pay(pc.getGrossPay())
                .net_pay(pc.getNetPay())
                .pay_period_start(pc.getPayPeriodStart())
                .pay_period_end(pc.getPayPeriodEnd())
                .insert(paycheckTable);
    }

    public List<Employee> getAllEmployees() {
        List<Map<String, Object>> employeeList = employeeTable.getRows();
        return employeeList.stream()
                .map(em -> {
                    Employee e = buildEmployee(em);
                    return e;
                })
                .collect(Collectors.toList());
    }

    public boolean checkRepeatedPay(Paycheck pc) {
        List<Map<String, Object>> rows = paycheckTable.getRows();
        long repeatedNum = rows.stream()
                .filter(m ->
                    (m.get("pay_period_end")).equals(pc.getPayPeriodEnd()) && m.get("employee_id").equals(pc.getEmployeeId())
                )
                .count();
        if (repeatedNum != 0) {
            System.err.println((String.format("有%s条重复的发薪记录: ", repeatedNum)
                    +"employee_id: " + pc.getEmployeeId()+", payPeriodEnd: "+ DateUtil.toDateStr(pc.getPayPeriodEnd())));
            return false;
        }
        return true;
    }

    public void setEmployeeTable(EmployeeTable employeeTable) {
        this.employeeTable = employeeTable;
    }

    public void setPaycheckTable(PaycheckTable paycheckTable) {
        this.paycheckTable = paycheckTable;
    }

    // ------------------------- private ----------------------------
    private Employee buildEmployee(Map<String, Object> em) {
        Employee employee = new Employee((int) em.get("id"), (String) em.get("name"), (String) em.get("address"));
        setClassification(employee, em);
        setSchedule(employee, em);
        setMethod(employee, em);
        setAffiliation(employee, em);
        return employee;
    }

    private void setAffiliation(Employee employee, Map<String, Object> em) {
        String affiliationType = (String) em.get("affiliation_type");
        Affiliation affiliation;
        switch (affiliationType) {
            case "0":
                affiliation = new NonAffiliation();
                break;
            case "1":
                affiliation = new UnionAffiliation(300);
                break;
            default:
                throw new PayrollException("无效支付类型");
        }
        employee.setAffiliation(affiliation);
    }

    private void setMethod(Employee employee, Map<String, Object> em) {
        String paymentType = (String) em.get("payment_type");
        PaymentMethod pm;
        switch (paymentType) {
            case "0":
                pm = new PostOfficeMethod();
                break;
            case "1":
                pm = new HoldMethod();
                break;
            case "2":
                pm = new BankMethod();
                break;
            default:
                throw new PayrollException("无效支付类型");
        }
        employee.setPaymentMethod(pm);
    }

    private void setSchedule(Employee employee, Map<String, Object> em) {
        String scheduleType = (String) em.get("schedule_type");
        PaymentSchedule ps;
        switch (scheduleType) {
            case "0":
                ps = new MonthlySchedule();
                break;
            case "1":
                ps = new WeeklySchedule();
                break;
            case "2":
                ps = new BiWeeklySchedule();
                break;
            default:
                throw new PayrollException("无效付薪日类型");
        }
        employee.setSchedule(ps);
    }

    private void setClassification(Employee employee, Map<String, Object> em) {
        String empType = (String) em.get("emp_type");
        PaymentClassification pc;
        switch (empType) {
            case "0":
                double salary = (double) em.get("salary");
                pc = new SalariedClassification(salary);
                break;
            case "1":
                double hourlyRate = (double) em.get("hourly_rate");
                pc = new HourlyClassification(hourlyRate);
                break;
            case "2":
                double baseSalary = (double) em.get("base_salary");
                double commissionRate = (double) em.get("commission_rate");
                pc = new CommissionClassification(baseSalary, commissionRate);
                break;
            default:
                throw new PayrollException("无效工资计算类型");
        }
        employee.setClassification(pc);
    }

}
