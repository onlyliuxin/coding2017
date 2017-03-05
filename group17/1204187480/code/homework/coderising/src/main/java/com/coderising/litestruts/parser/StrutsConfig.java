package com.coderising.litestruts.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luoziyihao on 3/5/17.
 */
public class StrutsConfig {
    public Map<String, Action> actions = new HashMap<>(10);

    public Map<String, Action> getActions() {
        return actions;
    }

    public void setActions(Map<String, Action> actions) {
        this.actions = actions;
    }

    public void addAction(Action action) {
        this.actions.put(action.getName(), action);
    }
}
