package com.coderising.myood.payroll.my_payroll;

import com.coderising.myood.payroll.my_payroll.database.DataBase;
import com.coderising.myood.payroll.my_payroll.database.EmployeeTable;
import com.coderising.myood.payroll.my_payroll.database.PaycheckTable;
import com.coderising.myood.payroll.my_payroll.domain.PayTransaction;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;

import java.util.Date;

/**
 * Created by thomas_young on 17/9/2017.
 * 定时任务驱动
 */
public class JobInvoker {
    private static DataBase payrollDataBase;
    private static EmployeeTable employeeTable;
    private static PaycheckTable paycheckTable;
    private  static PayTransaction payTransaction;
    public static void main(String[] args) {
        prepareData();
        Date d1 = DateUtil.parseDate("2017-08-23");
        Date d2 = DateUtil.parseDate("2017-09-30");
        DateUtil.dateBetween(d1, d2).forEach(d -> {
            initPayJob(d);
            payTransaction.execute();
            System.out.printf(DateUtil.toDateStr(d) + "*******************************\n");
        });
        // 查看Paycheck的在数据库中是否落表
        System.out.println(paycheckTable);
    }

    private static void initPayJob(Date date) {
        payTransaction = new PayTransaction();
        PayrollService payrollService = new PayrollService();
        payrollService.setEmployeeTable(employeeTable);
        payrollService.setPaycheckTable(paycheckTable);
        payTransaction.setDate(date);
        payTransaction.setPayrollService(payrollService);
    }

    private static void prepareData() {
        // 创建数据库，插入员工数据
        payrollDataBase = new DataBase("payroll");
        employeeTable = new EmployeeTable();
        paycheckTable = new PaycheckTable();
        payrollDataBase.addTable(paycheckTable);
        payrollDataBase.addTable(employeeTable);

        EmployeeTable.EmployeeBuilder employeeBuilder = new EmployeeTable.EmployeeBuilder();
        employeeBuilder
                .id(1)
                .name("yangkai")
                .address("shanghai")
                .emp_type("0")
                .salary(10000)
                .schedule_type("0")
                .payment_type("2")
                .affiliation_type("0")
                .insert(employeeTable);
        employeeBuilder.clear();
        employeeBuilder
                .id(2)
                .name("xiecantou")
                .emp_type("2")
                .address("changzhou")
                .commission_rate(0.2)
                .base_salary(5000)
                .schedule_type("2")
                .payment_type("0")
                .affiliation_type("1")
                .insert(employeeTable);
        employeeBuilder.clear();
        employeeBuilder
                .id(3)
                .name("guzhelun")
                .emp_type("1")
                .address("xianggang")
                .hourly_rate(500)
                .schedule_type("1")
                .payment_type("1")
                .affiliation_type("1")
                .insert(employeeTable);

    }

}
