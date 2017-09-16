package com.coderising.myood.payroll.my_payroll.database;


import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by thomas_young on 16/9/2017.
 */
public class DataBase {
    private String name;
    private List<Table> tables;


    public boolean addTable(Table table) {
        if (!tables.stream()
                .map(Table::getName)
                .collect(Collectors.toList())
                .contains(table.getName())) {
            tables.add(table);
            return true;
        }
        return false;
    }

    public Table getTable(String name) {
        return tables.stream()
                .filter(t -> name.equals(t.getName()))
                .findFirst()
                .orElse(null);
    }
}
