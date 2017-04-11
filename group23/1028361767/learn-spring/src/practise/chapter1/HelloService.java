package practise.chapter1;

import java.util.List;

public class HelloService {
	
	private String target;
	
	private List<String> targets;
	
	public HelloService(String target){
		this.target = target;
	}
	
	public String say(){
		return "hello " + this.target;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<String> getTargets() {
		return targets;
	}

	public void setTargets(List<String> targets) {
		this.targets = targets;
	}
	
}
