package com.coding.litestruts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Action {

    private String name;
    private String clazz;
    private List<Result> resultList = new ArrayList<>();

    public Action(String name, String clazz) {

        this.name = name;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<Result> getResultList() {
        return Collections.unmodifiableList(resultList);
    }

    public void addResultList(Result data) {
        this.resultList.add(data);
    }
}
