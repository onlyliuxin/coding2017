package com.coderising.myood.payroll.my_payroll.database;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class PaycheckTable extends AbstractTable {
    public PaycheckTable() {
        setName("Paycheck");
    }

    public static class PaycheckBuilder {
        private Map<String, Object> row;

        public PaycheckBuilder() {
            row = new HashMap<>();
        }

        public PaycheckBuilder pay_period_start(Date pay_period_start) {
            row.put("pay_period_start", pay_period_start);
            return this;
        }

        public PaycheckBuilder pay_period_end(Date pay_period_end) {
            row.put("pay_period_end", pay_period_end);
            return this;
        }

        public PaycheckBuilder gross_pay(double gross_pay) {
            row.put("gross_pay", gross_pay);
            return this;
        }

        public PaycheckBuilder net_pay(double net_pay) {
            row.put("net_pay", net_pay);
            return this;
        }

        public PaycheckBuilder employee_id(int employee_id) {
            row.put("employee_id", employee_id);
            return this;
        }

        public PaycheckBuilder deductions(double deductions) {
            row.put("deductions", deductions);
            return this;
        }

        public void clear() {
            row.clear();
        }

        public void insert(PaycheckTable paycheckTable) {
            paycheckTable.addRow(row);
        }

    }

}
