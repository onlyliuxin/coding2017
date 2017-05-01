package com.coderising.jvm.engine;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import com.coderising.jvm.method.Method;

public class ExecutorEngine {

	private Stack<StackFrame> stack = new Stack<StackFrame>();
	
	public ExecutorEngine() {
		
	}
	
	public void execute(Method mainMethod){
		StackFrame mainFrame = StackFrame.create(mainMethod);
		stack.push(mainFrame);
		while (!stack.isEmpty()) {
			StackFrame frame = stack.peek();
			ExecutionResult result = frame.execute();
			if (result.isPauseAndRunNewFrame()) {
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
	
	
	
	private void setupFunctionCallParams(StackFrame currentFrame,StackFrame nextFrame) {
		Method nextMethod = nextFrame.getMethod();
		int paramListSize = nextMethod.getParameterList().size();
		List<JavaObject> params = new LinkedList<>();
		for (int i = 0; i <= paramListSize; i++) {
			params.add(0, currentFrame.getOprandStack().pop());
		}
		nextFrame.setLocalVariableTable(params);
	}
	
}
