package com.coderising.myood.payroll.my_payroll.domain;

import com.coderising.myood.payroll.my_payroll.PayrollService;
import com.coderising.myood.payroll.my_payroll.database.DataBase;
import com.coderising.myood.payroll.my_payroll.database.EmployeeTable;
import com.coderising.myood.payroll.my_payroll.database.PaycheckTable;
import com.coderising.myood.payroll.my_payroll.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class RepeatedPayTest {
    private static DataBase payrollDataBase;
    private static EmployeeTable employeeTable;
    private static PaycheckTable paycheckTable;
    private  static PayTransaction payTransaction;
    @Test
    public void testRepeatedPay() {
        payrollDataBase = new DataBase("payroll");
        employeeTable = new EmployeeTable();
        paycheckTable = new PaycheckTable();
        payrollDataBase.addTable(employeeTable);
        payrollDataBase.addTable(paycheckTable);
        EmployeeTable.EmployeeBuilder employeeBuilder = new EmployeeTable.EmployeeBuilder();
        employeeBuilder
                .id(4)
                .name("牛逼哥")
                .emp_type("1")
                .address("Tokyo")
                .hourly_rate(500)
                .schedule_type("1")
                .payment_type("1")
                .affiliation_type("1")
                .insert(employeeTable);

        Date d = DateUtil.parseDate("2017-08-11");
        payTransaction = new PayTransaction();
        PayrollService payrollService = new PayrollService();
        payrollService.setEmployeeTable(employeeTable);
        payrollService.setPaycheckTable(paycheckTable);
        payTransaction.setDate(d);
        payTransaction.setPayrollService(payrollService);
        payTransaction.execute();
        payTransaction.execute();
        Assert.assertEquals(1, paycheckTable.getRows().size());

        d = DateUtil.parseDate("2017-08-18");
        payTransaction.setDate(d);
        payTransaction.execute();
        Assert.assertEquals(2, paycheckTable.getRows().size());
        System.out.println("***************************************");
        System.out.println(paycheckTable);
    }
}
