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

        /**
         * 员工类型
         * @param emp_type "0": salary, "1": hourly, "2": commission
         * @return
         */
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

        /**
         * 销售底薪
         * @param base_salary commission base salary
         * @return
         */
        public EmployeeBuilder base_salary(double base_salary) {
            row.put("base_salary", base_salary);
            return this;
        }

        public EmployeeBuilder commission_rate(double commission_rate) {
            row.put("commission_rate", commission_rate);
            return this;
        }

        /**
         * 发薪日类型
         * @param schedule_type "0": monthly, "1": weekly, "2": double weekly
         * @return
         */
        public EmployeeBuilder schedule_type(String schedule_type) {
            row.put("schedule_type", schedule_type);
            return this;
        }

        /**
         * 支付方式类型
         * @param payment_type "0": post office, "1": hold, "2": bank
         * @return
         */
        public EmployeeBuilder payment_type(String payment_type) {
            row.put("payment_type", payment_type);
            return this;
        }

        /**
         * 是否参加协会
         * @param affiliation_type "0": 未参加, "1": 参加
         * @return
         */
        public EmployeeBuilder affiliation_type(String affiliation_type) {
            row.put("affiliation_type", affiliation_type);
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
