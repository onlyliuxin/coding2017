package week02.litestruts;

import java.util.Map;

/**
 * @author Hui Zhou
 * @version 1.0 2017-02-28
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
