package com.github.ipk2015.coding2017.minijvm.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.github.ipk2015.coding2017.minijvm.method.Method;



public class ExecutorEngine {

	private Stack<StackFrame> stack = new Stack<StackFrame>();
	
	public ExecutorEngine() {
		
	}
	
	public void execute(Method mainMethod){
		StackFrame stackFrame = StackFrame.create(mainMethod);
		stack.push(stackFrame);
		
		while(!stack.isEmpty()){
			StackFrame frame = stack.peek();
			ExecutionResult executionResult = frame.execute();
			if(executionResult.isPauseAndRunNewFrame()){
				Method nextMethod = executionResult.getNextMethod();
				StackFrame nextFrame = StackFrame.create(nextMethod);
				nextFrame.setCallerFrame(frame);
				setupFunctionCallParams(frame,nextFrame);
				stack.push(nextFrame);
			}else{
				stack.pop();
			}
		}
	}
	
	
	
	private void setupFunctionCallParams(StackFrame currentFrame,StackFrame nextFrame) {
		Method nextMethod = nextFrame.getMethod();
		List<String> parameterList = nextMethod.getParameterList();
		
		int paramSize = parameterList.size()+1;
		List<JavaObject> params = new ArrayList<JavaObject>();
		while(paramSize > 0){
			params.add(currentFrame.getOprandStack().pop());
			paramSize--;
		}
		Collections.reverse(params);
		nextFrame.setLocalVariableTable(params);
	}
	
}
