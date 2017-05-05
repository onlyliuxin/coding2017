package com.coding2017.litestruts;

import java.util.List;

/**
 * Created by kaitao.li on 2017/3/4.
 */
public class StrutsDefinition {
    private List<ActionDefinition> actionDefinitionList;

    public List<ActionDefinition> getActionDefinitionList() {
        return actionDefinitionList;
    }

    public void setActionDefinitionList(List<ActionDefinition> actionDefinitionList) {
        this.actionDefinitionList = actionDefinitionList;
    }

    public static class ActionDefinition {
        private String name;
        private String clazz;
        private List<ResultDefinition> resultDefinitions;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public List<ResultDefinition> getResultDefinitions() {
            return resultDefinitions;
        }

        public void setResultDefinitions(List<ResultDefinition> resultDefinitions) {
            this.resultDefinitions = resultDefinitions;
        }
    }

    public static class ResultDefinition {
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
}