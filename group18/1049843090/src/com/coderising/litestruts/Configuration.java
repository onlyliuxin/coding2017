package com.coderising.litestruts;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;
import java.util.Map;

/**
 * Struts.xml Config
 */
public class Configuration {

    private Map<String, Action> actionMap;

    public Configuration(String fileName) {
        //TODO 读出XML并将结构保存到actionMap
    }

    public String getClassName(String actionName) {
        Action action = actionMap.get(actionName);
        if (action == null) {
            return null;
        }
        return action.getClassName();
    }

    public String getResultJsp(String actionName, String result) {
        Action action = actionMap.get(actionName);
        if (action == null) {
            return null;
        }
        return action.getResult().get(result);
    }


    private static class Action {
        private String name;
        private String className;
        private Map<String, String> result;


        public String getName() {
            return name;
        }

        public String getClassName() {
            return className;
        }

        public Map<String, String> getResult() {
            return result;
        }

        public Action(String name, String className, Map<String, String> result) {
            this.name = name;
            this.className = className;
            this.result = result;
        }
    }

}
