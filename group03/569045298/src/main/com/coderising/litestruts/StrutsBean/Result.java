package com.coderising.litestruts.StrutsBean;

import java.io.Serializable;

/**
 * Created by zt on 2017/3/1.
 */
public class Result implements Serializable {

    private String name;

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
