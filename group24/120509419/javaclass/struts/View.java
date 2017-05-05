/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaclass.struts;

import java.util.Map;

public class View {

    private String jsp;
    private Map parameters;

    public String getJsp() {
        return jsp;
    }

    public View setJsp(String jsp) {
        this.jsp = jsp;
        return this;
    }

    public Map getParameters() {
        return parameters;
    }

    public View setParameters(Map parameters) {
        this.parameters = parameters;
        return this;
    }
}
