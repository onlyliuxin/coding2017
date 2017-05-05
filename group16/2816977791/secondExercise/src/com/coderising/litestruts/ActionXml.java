package com.coderising.litestruts;

import java.util.Map;

/**
 * @author nvarchar
 *         date 2017/3/1
 */
public class ActionXml {
    private String name;
    private String className;
    private Map<String, String> map;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
