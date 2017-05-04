package miniJVM.engine;


import miniJVM.cmd.ByteCodeCommand;
import miniJVM.method.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackFrame {
	
	private List<JavaObject> localVariableTable = new ArrayList<JavaObject>();
	private Stack<JavaObject> operandStack = new Stack<JavaObject>();
	
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
		StackFrame frame = new StackFrame(m);

		return frame;
	}

	
	private StackFrame(Method m) {		
		this.m = m;
	}

	public JavaObject getLocalVariableValue(int index){
		return this.localVariableTable.get(index);
	}
	
	public Stack<JavaObject> getOperandStack(){
		return this.operandStack;
	}
	
	public int getNextCommandIndex(int offset){
		
		ByteCodeCommand[] cmds = m.getCodeAttr().getCmds();
		for(int i=0;i<cmds.length; i++){
			if(cmds[i].getOffset() == offset){
				return i;
			}
		}
		throw new RuntimeException("Can't find next command");
	}
	
	public ExecutionResult execute(){
		ByteCodeCommand[] cmds = m.getCmds();

		while(index < cmds.length){
			ExecutionResult result = new ExecutionResult();
			result.setNextAction(ExecutionResult.RUN_NEXT_CMD);

			System.out.println("command -> " + cmds[index].toString());
			cmds[index].execute(this, result);

			if(result.isRunNextCmd()){//执行下一个指令 -> index直接+1
				index++;
			}else if(result.isPauseAndRunNewFrame()){//执行新的函数栈帧 -> index+1并返回该栈帧，保存index
				index ++;
				return result;
			}else if(result.isExitCurrentFrame()){//退出当栈帧 -> 直接返回
				return result;
			}else if(result.isJump()){//跳到另外的函数栈帧 -> 获取要跳至的index
				index = getNextCommandIndex(result.getNextCmdOffset());
			}else index ++;
		}

		ExecutionResult result = new ExecutionResult();
		result.setNextAction(ExecutionResult.EXIT_CURRENT_FRAME);
		return result;
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
