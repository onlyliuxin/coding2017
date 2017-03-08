package com.coderising.litestruts.StrutsBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zt on 2017/3/1.
 */
public class Action implements Serializable {

    private String name;

    private String className;

    private List<Result> result;

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

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
