package com.coding.mini_jvm.src.com.coderising.jvm.engine;


import com.coding.mini_jvm.src.com.coderising.jvm.method.Method;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ExecutorEngine {

	private Stack<StackFrame> stack = new Stack<StackFrame>();

	public ExecutorEngine() {

	}

	public void execute(Method mainMethod) {
		stack.push(StackFrame.create(mainMethod));
		while (!stack.isEmpty()) {
			//1 执行栈帧
			StackFrame frame = stack.peek();
		    ExecutionResult executionResult = frame.execute();
			if (executionResult.isPauseAndRunNewFrame()) {
				Method method = executionResult.getNextMethod();
				StackFrame nextFrame = StackFrame.create(method);
				nextFrame.setCallerFrame(frame);
				setupFunctionCallParams(frame, nextFrame);
				stack.push(nextFrame);
			} else {
				stack.pop();
			}
		}

	}


	private void setupFunctionCallParams(StackFrame currentFrame,StackFrame nextFrame) {

		Method method = nextFrame.getMethod();
		List<String> params = method.getParameterList();
		int paramNum = params.size();
		if (!method.isStatic()) {
			paramNum++;
		}
		List<JavaObject> args = new ArrayList<>();
		while (paramNum > 0) {
			args.add(currentFrame.getOprandStack().pop());
			paramNum--;
		}
		Collections.reverse(args);
		nextFrame.setLocalVariableTable(args);
	}
	
}
