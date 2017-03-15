package com.circle.struts;

import java.util.Map;

/**
 * Created by keweiyang on 2017/3/2.
 */
public class View {
    private String jsp;
    private Map<String, String> parameters;

    public String getJsp() {
        return jsp;
    }

    public void setJsp(String jsp) {
        this.jsp = jsp;
    }

    public Map getParameters() {
        return parameters;
    }

    public void setParameters(Map parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("jsp= ");
        builder.append(this.jsp);

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            builder.append(" , ");
            builder.append(entry.getKey());
            builder.append(" = ");
            builder.append(entry.getValue());
        }


        return builder.toString();
    }
}
