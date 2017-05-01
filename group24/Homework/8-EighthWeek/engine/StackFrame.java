package com.coderising.jvm.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.method.Method;

public class StackFrame {
	
	private List<JavaObject> localVariableTable = new ArrayList<JavaObject>();
	private Stack<JavaObject> oprandStack = new Stack<JavaObject>();
	
	int index = 0;
	
	private Method m = null;
	
	private StackFrame callerFrame = null;
	
	public StackFrame getCallerFrame() {
		return callerFrame;
	}

	public void setCallerFrame(StackFrame callerFrame) {
		this.callerFrame = callerFrame;
	}

	
	
	
	public static  StackFrame create(Method m){
		
		StackFrame frame = new StackFrame( m );			
		
		return frame;
	}

	
	private StackFrame(Method m) {		
		this.m = m;
		
	}
	
	
	
	public JavaObject getLocalVariableValue(int index){
		return this.localVariableTable.get(index);
	}
	
	public Stack<JavaObject> getOprandStack(){
		return this.oprandStack;
	}
	
	public int getNextCommandIndex(int offset){
		
		ByteCodeCommand [] cmds = m.getCodeAttr().getCmds();
		for(int i=0;i<cmds.length; i++){
			if(cmds[i].getOffset() == offset){
				return i;
			}
		}
		throw new RuntimeException("Can't find next command");
	}
	
	public ExecutionResult execute(){
		return null;
		
	}

	
	
	
	public void setLocalVariableTable(List<JavaObject> values){
		this.localVariableTable = values;	
	}
	
	public void setLocalVariableValue(int index, JavaObject jo){
		//问题： 为什么要这么做？？
		if(this.localVariableTable.size()-1 < index){
			for(int i=this.localVariableTable.size(); i<=index; i++){
				this.localVariableTable.add(null);
			}
		}
		this.localVariableTable.set(index, jo);
		
		
	}
	
	public Method getMethod(){
		return m;
	}
	

}
