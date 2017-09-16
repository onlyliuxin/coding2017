package com.coderising.myood.payroll.my_payroll.database;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomas_young on 16/9/2017.
 */
public class EmployeeTable extends AbstractTable {
    public EmployeeTable() {
        setName("Employee");
    }

    public static class EmployeeBuilder {
        private Map<String, Object> row;
        private int id;
        private String name;
        private String address;
        private String emp_type;  // "0": salary, "1": hourly, "2": commission
        private double hourly_rate;
        private double salary;
        private double base_salary;  // commission base salary
        private double commission_rate;
        private String schedule_type;  // "0": monthly, "1": weekly, "2": double weekly
        private String payment_type;  // "0": post office, "1": hold, "2": bank

        @Override
        public String toString() {
            return "EmployeeBuilder{}";
        }

        public EmployeeBuilder() {
            row = new HashMap<>();
        }

        public EmployeeBuilder id(int id) {
            row.put("id", id);
            return this;
        }

        public EmployeeBuilder name(String name) {
            row.put("name", name);
            return this;
        }

        public EmployeeBuilder address(String address) {
            row.put("address", address);
            return this;
        }

        public EmployeeBuilder emp_type(String emp_type) {
            row.put("emp_type", emp_type);
            return this;
        }

        public EmployeeBuilder hourly_rate(double hourly_rate) {
            row.put("hourly_rate", hourly_rate);
            return this;
        }

        public EmployeeBuilder salary(double salary) {
            row.put("salary", salary);
            return this;
        }

        public EmployeeBuilder base_salary(double base_salary) {
            row.put("base_salary", base_salary);
            return this;
        }

        public EmployeeBuilder commission_rate(double commission_rate) {
            row.put("commission_rate", commission_rate);
            return this;
        }

        public EmployeeBuilder schedule_type(String schedule_type) {
            row.put("schedule_type", schedule_type);
            return this;
        }

        public EmployeeBuilder payment_type(String payment_type) {
            row.put("payment_type", payment_type);
            return this;
        }

        public void clear() {
            row.clear();
        }

        public void insert(EmployeeTable employeeTable) {
            employeeTable.addRow(row);
        }

    }

}
