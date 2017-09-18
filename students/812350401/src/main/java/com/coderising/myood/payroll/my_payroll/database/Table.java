package com.coderising.myood.payroll.my_payroll.database;

import java.util.List;
import java.util.Map;

/**
 * Created by thomas_young on 16/9/2017.
 */
public interface Table {
    String getName();
    List<Map<String, Object>> getRows();
    void addRow(Map<String, Object> row);
}
