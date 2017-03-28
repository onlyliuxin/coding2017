package com.yang.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Dev_yang on 2017/3/5.
 */
public class Action implements Serializable{

    private List<Result> results;

    private String name;

    private String packageName;


    @XmlElement(name = "result")
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }


    @XmlAttribute(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @XmlAttribute(name = "class")
    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    @Override
    public String toString() {
        return "Action{" +
                "results=" + results +
                ", name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
