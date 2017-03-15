package com.aaront.exercise.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tonyhui
 * @since 17/2/27
 */
public class Action {
    private String name;
    private String clazz;
    private List<Result> results = new ArrayList<>();

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

    public void addResult(Result result) {
        this.results.add(result);
    }

    public List<Result> getResults() {
        return results;
    }
}
