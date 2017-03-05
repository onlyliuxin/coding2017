package com.circle.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by keweiyang on 2017/3/5.
 */
public class StrutsEntity {

    private List<ActionEntity> actionEntityList = new ArrayList<>();

    public List<ActionEntity> getActionEntityList() {
        return actionEntityList;
    }

    public void setActionEntityList(List<ActionEntity> actionEntityList) {
        this.actionEntityList = actionEntityList;
    }
}
