
package com.yang.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;



@XmlRootElement(name = "struts")
public class Struts implements Serializable{

    private List<Action> actions;


    @XmlElement(name = "action")
    public List<Action> getActions() {
        return actions;
    }


    public void setActions(List<Action> actions) {
        this.actions = actions;
    }


    @Override
    public String toString() {
        return "Struts{" +
                "actions=" + actions +
                '}';
    }
}
