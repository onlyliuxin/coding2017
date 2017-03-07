package org.xukai.coderising.litestruts;

import java.util.Map;

/**
 * @author xukai
 * @desc
 * @date 2017-02-27-下午 2:59
 */
public class Action {

    private String name;

    private Class<?> aClass;

    private Map<String,String> resultMapping;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getaClass() {
        return aClass;
    }

    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }

    public Map<String, String> getResultMapping() {
        return resultMapping;
    }

    public void setResultMapping(Map<String, String> resultMapping) {
        this.resultMapping = resultMapping;
    }
}
