package main.coding_170302;

import java.util.Map;

/**
 * Created by peter on 2017/3/3.
 */
public class View {
    private String jsp;
    private Map parameters;

    public View setJsp(String jsp){
        this.jsp = jsp;
        return this;
    }
    public View setPatameters(Map parameters){
        this.parameters = parameters;
        return this;
    }
    public String getJsp() {
        return jsp;
    }

    public Map getParameters() {
        return parameters;
    }
}
