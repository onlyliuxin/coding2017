package com.coderising.myood.payroll.my_payroll.database;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * EmployeeTable Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 16, 2017</pre>
 */
public class EmployeeTableTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testEmployeeTable() {
        Table table = new EmployeeTable();
        EmployeeTable.EmployeeBuilder employeeBuilder = new EmployeeTable.EmployeeBuilder();
        employeeBuilder.id(1)
                .address("aa")
                .insert((EmployeeTable) table);
        employeeBuilder
                .id(2)
                .base_salary(500.5)
                .insert((EmployeeTable) table);
        employeeBuilder.clear();
        employeeBuilder
                .id(3)
                .salary(5000)
                .emp_type("0")
                .insert((EmployeeTable) table);
        System.out.println(table);
    }

    @Test
    public void testDatabase() {
        DataBase dataBase = new DataBase("payroll");
        Table table = new EmployeeTable();

        EmployeeTable.EmployeeBuilder employeeBuilder = new EmployeeTable.EmployeeBuilder();
        employeeBuilder.id(1)
                .address("aa")
                .insert((EmployeeTable) table);
        employeeBuilder
                .id(2)
                .base_salary(500.5)
                .insert((EmployeeTable) table);
        employeeBuilder.clear();
        employeeBuilder
                .id(3)
                .salary(5000)
                .emp_type("0")
                .insert((EmployeeTable) table);
        dataBase.addTable(table);

        System.out.println(dataBase);

        System.out.println(dataBase.drop("Employee"));
        System.out.println(dataBase);
    }

}
