package com.coderising.litestruts.api;

import java.io.Serializable;
import java.util.Map;

public class View implements Serializable {

    private static final long serialVersionUID = 3253386991720311844L;

    private String jsp;

    private Map<Object, Object> parameters;

    public String getJsp() {
        return jsp;
    }

    public View setJsp(String jsp) {
        this.jsp = jsp;
        return this;
    }

    public Map getParameters() {
        return parameters;
    }

    public View setParameters(Map<Object, Object> parameters) {
        this.parameters = parameters;
        return this;
    }
}
