package week8.jvm.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import week8.jvm.attr.CodeAttr;
import week8.jvm.cmd.ByteCodeCommand;
import week8.jvm.method.Method;

public class StackFrame {

	private List<JavaObject> localVariableTable=new ArrayList<>();
	private Stack<JavaObject> operandStack=new Stack<>();
	private Method method=null;
	private StackFrame callerFrame=null;
	
	int index=0;
	
	private StackFrame(Method method){
		this.method=method;
	}
	
	public static StackFrame createFrame(Method method){
		StackFrame stackFrame=new StackFrame(method);
		return stackFrame;
	}
	
	public ExecutorResult execute(){
		
		ByteCodeCommand[] cmds=method.getCmds();
		
		while(index < cmds.length){
			ExecutorResult result=new ExecutorResult();
			
			//默认执行下一条指令
			result.setNextAction(ExecutorResult.RUN_NEXT_CMD);
			
			System.out.println(cmds[index]);
			
			cmds[index].execute(this,result);
			
			if(result.isRunNextCmd()){
				index++;
			}else if(result.isPauseAndRunNewFrame()){
				index++;
				return result;
			}else if(result.isExitCurrentFrame()){
				return result;
			}else if(result.isJump()){
			    int offset=result.getNextCmdOffset();
			    this.index=getNextCommandIndex(offset);
			}else{
				index++;
			}
		}
		
		//当前StackFrmae的指令全部执行完毕，可以退出了
		ExecutorResult result=new ExecutorResult();
		result.setNextAction(ExecutorResult.EXIT_CURRENT_FRAME);
		return result;
	}
	
	private int getNextCommandIndex(int offset){
		ByteCodeCommand[] cmds=method.getCmds();
		for(int i=0;i<cmds.length;i++){
			if(cmds[i].getOffset() == offset){
				return i;
			}			
		}
		throw new RuntimeException("Can't find next command");
	}
	
	public void setLocalVariableValue(int index,JavaObject jo){
		
		//防止越界异常
		if(localVariableTable.size()-1 < index){
			for(int i=localVariableTable.size();i<=index;i++){
				localVariableTable.add(null);
			}
		}
		
		this.localVariableTable.set(index, jo);
	}

	public JavaObject getLocalVariableValue(int index){
		return localVariableTable.get(index);
	}
	
	public JavaObject getLocalVariableTable(int index) {
		return localVariableTable.get(index);
	}
	public void setLocalVariableTable(List<JavaObject> localVariableTable) {
		this.localVariableTable = localVariableTable;
	}
	
	public Stack<JavaObject> getOperandStack() {
		return operandStack;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public StackFrame getCallerFrame() {
		return callerFrame;
	}
	public void setCallerFrame(StackFrame callerFrame) {
		this.callerFrame = callerFrame;
	}

}
