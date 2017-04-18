package com.coderising.litestruts.dom;

import java.util.Map;

/**
 * Created by Korben on 03/03/2017.
 */
public class StrutsAction {
    private String actionClassName;
    private Map<String, String> attributes;

    public String getActionClassName() {
        return actionClassName;
    }

    public void setActionClassName(String actionClassName) {
        this.actionClassName = actionClassName;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
