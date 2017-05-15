package com.coderising.jvm.engine;

import java.util.ArrayList;
import java.util.List;

public class OperandStack {
	private List<JavaObject> operands = new ArrayList<JavaObject>();
	
	public void push(JavaObject jo){
		operands.add(jo);
	}
	public JavaObject pop(){
		int index = size()-1;
		JavaObject jo = (JavaObject)operands.get(index);
		operands.remove(index);
		return jo;
		
	}
	public JavaObject top(){
		int index = size()-1;
		return (JavaObject)operands.get(index);
	}
	public int size(){
		return operands.size();		
	}
}
