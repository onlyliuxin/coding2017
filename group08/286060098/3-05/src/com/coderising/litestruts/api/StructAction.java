package com.coderising.litestruts.api;

import java.io.Serializable;
import java.util.Map;

/**
 * @author haipop Date: 17-3-2 Time: 下午3:18
 */
public class StructAction implements Serializable {

    private static final long serialVersionUID = -8955018500533743847L;

    private String name;

    private String clazzName;

    private Map<String, String> actions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Map<String, String> getActions() {
        return actions;
    }

    public void setActions(Map<String, String> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "StructAction{" + "name='" + name + '\'' + ", clazzName='" + clazzName + '\'' + ", actions=" + actions
                + '}';
    }
}