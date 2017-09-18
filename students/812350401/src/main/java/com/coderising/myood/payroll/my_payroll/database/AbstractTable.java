package com.coderising.myood.payroll.my_payroll.database;

import java.util.*;

/**
 * Created by thomas_young on 16/9/2017.
 */
public abstract class AbstractTable implements Table {
    private String name;
    protected List<Map<String, Object>> rows = new LinkedList<>();

    public String getName() {
        return name;
    }

    // 不考虑唯一键问题
    public void addRow(Map<String, Object> row) {
        Map<String, Object> cloneRow = new HashMap<>();
        cloneRow.putAll(row);
        rows.add(cloneRow);
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("table name: ")
                .append(name)
                .append("\n")
                .append(rows.toString());
        return ret.toString();
    }
}

