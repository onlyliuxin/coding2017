package com.coderising.myood.payroll.my_payroll.database;


import javax.xml.crypto.Data;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by thomas_young on 16/9/2017.
 */
public class DataBase {
    private String name;
    private List<Table> tables = new LinkedList<>();

    public DataBase(String name) {
        this.name = name;
    }

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

    public boolean drop(String name) {
        Table table = tables
                .stream()
                .filter(t -> name.equals(t.getName()))
                .findFirst()
                .orElse(null);
        if (table != null) {
            tables.remove(table);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "DataBase{" +
                "database name='" + name + "', " +
                "\n" +
                "tables=" + tables +
                '}';
    }
}
