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
	
	public void addLocalVariableValue(JavaObject jo){
		this.localVariableTable.add(jo);
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
		ByteCodeCommand[] cmds = m.getCmds();
		ExecutionResult result = new ExecutionResult();
		ByteCodeCommand nextCmd = m.getCmds()[index];
		int watchdog = 55;
		while(true && watchdog -->0){
			System.out.println("run cmd: " + nextCmd.getReadableCodeText());
			nextCmd.execute(this, result);
			if(result.isExitCurrentFrame()	){
				break;
			}
			else if(result.isPauseAndRunNewFrame()){
				index ++;
				return result;
			}
			else{
				index ++;
				nextCmd = cmds[index];
			}
		}
		return result;
	}

	
	public List<JavaObject> getLocalVariableTable(){
		return localVariableTable;
	}
	
	public void setLocalVariableTable(List<JavaObject> values){
		this.localVariableTable = values;	
	}
	
	public void setLocalVariableValue(int index, JavaObject jo){
		//问题： 为什么要这么做？？Extending a space for the new jo
		if(this.localVariableTable.size()-1 < index){
			for(int i=this.localVariableTable.size(); i<=index; i++){
				this.localVariableTable.add(null);
			}
		}
		this.localVariableTable.set(index, jo);
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("OperandStack: \n------\n");
		oprandStack.forEach(x->{
			sb.append(x + "\n");
		});
		sb.append("------\n");
		sb.append("LocalVariable: \n[");
		localVariableTable.forEach(x->{
			sb.append(x + " ");
		});
		sb.append("]");
		return sb.toString();
	}
	
	public Method getMethod(){
		return m;
	}
	

}
