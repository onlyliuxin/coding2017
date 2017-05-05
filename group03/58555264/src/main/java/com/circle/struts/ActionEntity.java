package com.circle.struts;

import java.util.*;

/**
 * Created by keweiyang on 2017/3/2.
 */
class ActionEntity {

    private String name;
    private String className;

    private Map<String, String> resultMap = new HashMap<>();

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

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("name= ");
        builder.append(this.name);
        builder.append(" ,className= ");
        builder.append(this.className);


        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            builder.append(" , resultName= ");
            builder.append(entry.getKey());
            builder.append(" , jsp= ");
            builder.append(entry.getValue());
        }


        return builder.toString();
    }

}
