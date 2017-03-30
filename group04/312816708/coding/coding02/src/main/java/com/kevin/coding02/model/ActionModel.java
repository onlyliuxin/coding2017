package com.kevin.coding02.model;

import java.util.List;

/**
 * Created by YinWenBing on 2017/2/28.
 */
public class ActionModel {
    private String actionName;
    private String actionClass;
    private List<ResultModel> results;

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionClass() {
        return actionClass;
    }

    public void setActionClass(String actionClass) {
        this.actionClass = actionClass;
    }

    public List<ResultModel> getResults() {
        return results;
    }

    public void setResults(List<ResultModel> results) {
        this.results = results;
    }
}
