package com.aaront.exercise.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tonyhui
 * @since 17/2/27
 */
public class Structs {
    private List<Action> actions = new ArrayList<>();

    public void addAction(Action action) {
        actions.add(action);
    }

    public List<Action> getActions() {
        return actions;
    }
}
