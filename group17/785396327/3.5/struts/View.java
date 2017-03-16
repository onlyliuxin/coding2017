package struts;

import java.util.Map;

/**
 * Created by IBM on 2017/3/4.
 */
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
