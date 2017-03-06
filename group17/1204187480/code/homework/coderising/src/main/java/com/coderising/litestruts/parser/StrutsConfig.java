package com.coderising.litestruts.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luoziyihao on 3/5/17.
 */
public class StrutsConfig {
    public Map<String, ActionConfig> actions = new HashMap<>(10);

    public Map<String, ActionConfig> getActions() {
        return actions;
    }

    public void setActions(Map<String, ActionConfig> actions) {
        this.actions = actions;
    }

    public void addAction(ActionConfig action) {
        this.actions.put(action.getName(), action);
    }
}
