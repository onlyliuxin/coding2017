package com.coderising.jvm.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.coderising.jvm.attr.CodeAttr;
import com.coderising.jvm.clz.ClassFile;
import com.coderising.jvm.cmd.ByteCodeCommand;
import com.coderising.jvm.constant.MethodRefInfo;
import com.coderising.jvm.method.Method;

public class ExecutorEngine {

	private Stack<StackFrame> stack = new Stack<StackFrame>();
	
	public ExecutorEngine() {
		
	}
	
	public void execute(Method mainMethod){
		
		StackFrame mainFrame = StackFrame.create(mainMethod);
		stack.push(mainFrame);
		
		while(!stack.isEmpty()) {
			StackFrame frame = stack.peek();
			
			ExecutionResult result = frame.execute();
			
			if(result.isPauseAndRunNewFrame()) {
				Method nextMethod = result.getNextMethod();
				StackFrame nextFrame = StackFrame.create(nextMethod);
				nextFrame.setCallerFrame(frame);
				setupFunctionCallParams(frame, nextFrame);
				
				stack.push(nextFrame);
			} else {
				stack.pop();
			}
		}
	}
	
	
	/**
	 * 将操作数栈中的操作数load到下一个方法栈的局部变量表中
	 * @param currentFrame
	 * @param nextFrame
	 */
	private void setupFunctionCallParams(StackFrame currentFrame,StackFrame nextFrame) {
		
		Method nextMethod = nextFrame.getMethod();
		List<String> paramList = nextMethod.getParameterList();
		//要把this也传递进去
		int paramNum = paramList.size() + 1;
		
		ArrayList<JavaObject> values = new ArrayList<JavaObject>();
		
		while(paramNum>0) {
			values.add(currentFrame.getOprandStack().pop());
			paramNum --;
		}
		//倒序排列vlaues
		ArrayList<JavaObject> params = new ArrayList<JavaObject>();
		
		for(int i = values.size()-1; i>=0; i--) {
			params.add(values.get(i));
		}
		
		nextFrame.setLocalVariableTable(params);
	}
	
}
