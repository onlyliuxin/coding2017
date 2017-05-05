package week8.jvm.engine;

import java.util.ArrayList;
import java.util.List;

public class OperandStack {

	private List<JavaObject> operands=new ArrayList<>();
	
	public void push(JavaObject jo){
		operands.add(jo);
	}
	
	public JavaObject getTop(){
		 return operands.get(getSize()-1);
	}
	
	public JavaObject pop(){
		int index=getSize()-1;
		JavaObject jo=operands.get(index);
		operands.remove(index);
		return jo;
	}
	
	public int getSize(){
		return operands.size();
	}
}
