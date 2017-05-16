package com.dudy.learn01.litestruts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dudy on 2017/3/1.
 * 解析xml对象
 *
 *
 */
public class ActionPojoParseXML {

    private  String  name;
    private  String  classname;

    private Map<String,String > childElement;


    public ActionPojoParseXML(String name, String classname) {
        this.name = name;
        this.classname = classname;
        childElement = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Map<String, String> getChildElement() {
        return childElement;
    }

    public void setChildElement(Map<String, String> childElement) {
        this.childElement = childElement;
    }

    @Override
    public String toString() {
        return "ActionPojoParseXML{" +
                "name='" + name + '\'' +
                ", classname='" + classname + '\'' +
                ", childElement=" + childElement +
                '}';
    }
}
