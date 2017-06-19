package com.coderising.litestruts.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luoziyihao on 3/5/17.
 */
public class ActionConfig {
    private String name;
    private String className;
    private Map<String, Result> results = new HashMap<>(10);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, Result> getResults() {
        return results;
    }

    public void setResults(Map<String, Result> results) {
        this.results = results;
    }

    public void addResult(Result result) {
        this.results.put(result.getName(), result);
    }


}
