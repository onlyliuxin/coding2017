package week2_miniStruts;
import java.util.Map;
public class View {
	private String jsp;
	@SuppressWarnings("rawtypes")
	private Map parameters;
	
	public String getJsp(){
		return jsp;
	}
	public View setJsp(String jsp){
		this.jsp = jsp;
		return this;
	}
	@SuppressWarnings("rawtypes")
	public Map getParameters(){
		return parameters;
	}
	@SuppressWarnings("rawtypes")
	public View setParameters(Map parameters){
		this.parameters = parameters;
		return this;
	}
}
