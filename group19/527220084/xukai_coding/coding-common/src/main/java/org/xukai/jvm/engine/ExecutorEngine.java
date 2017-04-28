package org.xukai.jvm.engine;

import org.xukai.jvm.method.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExecutorEngine {

	private Stack<StackFrame> stack = new Stack<StackFrame>();
	
	public ExecutorEngine() {
		
	}
	
	public void execute(Method mainMethod){
		StackFrame MainFrame = StackFrame.create(mainMethod);
		stack.push(MainFrame);
		while(!stack.empty()){
			StackFrame currentFrame = stack.peek();
			ExecutionResult result = currentFrame.execute();
			if (result.isPauseAndRunNewFrame()) {
				StackFrame nextFrame = currentFrame.create(result.getNextMethod());
				nextFrame.setCallerFrame(currentFrame);
				setupFunctionCallParams(currentFrame, nextFrame);
				stack.push(nextFrame);
			} else {
				stack.pop();
			}
		}


	}
	
	
	
	private void setupFunctionCallParams(StackFrame currentFrame,StackFrame nextFrame) {
		List<String> parameterList = nextFrame.getMethod().getParameterList();
		if (parameterList.size() > 0) {

		}
		ArrayList<JavaObject> localVariabs = new ArrayList<>();
		for (int i = 0; i < parameterList.size() + 1; i++) {
			JavaObject javaObject = currentFrame.getOprandStack().pop();
			localVariabs.add(javaObject);
		}
		ArrayList<JavaObject> params = new ArrayList<>();
		for (int i = localVariabs.size(); i > 0; i--) {
			params.add(localVariabs.get(i));
		}
		nextFrame.setLocalVariableTable(params);



	}
	
}
