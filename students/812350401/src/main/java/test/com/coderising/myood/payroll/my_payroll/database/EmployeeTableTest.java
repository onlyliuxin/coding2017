package test.com.coderising.myood.payroll.my_payroll.database;

import com.coderising.myood.payroll.my_payroll.database.EmployeeTable;
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
        EmployeeTable table = new EmployeeTable();
        EmployeeTable.EmployeeBuilder employeeBuilder = new EmployeeTable.EmployeeBuilder();
        employeeBuilder.id(1)
                .address("aa")
                .insert(table);
        employeeBuilder
                .id(2)
                .base_salary(500.5)
                .insert(table);
        employeeBuilder.clear();
        employeeBuilder
                .id(3)
                .salary(5000)
                .emp_type("0")
                .insert(table);
        System.out.println(table);
    }

}
