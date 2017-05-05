package com.java.xiaoqin.litestruts.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoqin on 17-3-5.
 */
public class ActionBean {
    private String className;
    private Map<String, String> result;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

    public void addResult(String name, String text) {
        if (null == result) result = new HashMap<>();
        result.put(name, text);
    }

    @Override
    public String toString() {
        return "ActionBean{" +
                "className='" + className + '\'' +
                ", result=" + result +
                '}';
    }
}
