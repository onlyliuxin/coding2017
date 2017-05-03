package miniJVM.engine;


import miniJVM.method.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExecutorEngine {

	private Stack<StackFrame> stack = new Stack<StackFrame>();


	public ExecutorEngine() {}
	
	public void execute(Method mainMethod){

		StackFrame main = StackFrame.create(mainMethod);
		stack.push(main);
		
		while(!stack.isEmpty()){

			StackFrame currFrame = stack.peek();

			ExecutionResult result = currFrame.execute();
			if(result.isPauseAndRunNewFrame()){
				Method nextMethod = result.getNextMethod();
				StackFrame nextFrame = StackFrame.create(nextMethod);
				nextFrame.setCallerFrame(currFrame);
				setupFunctionCallParams(currFrame, nextFrame);

				stack.push(nextFrame);
			}else{
				stack.pop();
			}
		}
		
	}

	private void setupFunctionCallParams(StackFrame currentFrame,StackFrame nextFrame) {
		Method nextMethod = nextFrame.getMethod();
		List<String> paramList = nextMethod.getParammeterList();

		int paramNum = paramList.size() + 1;

		List<JavaObject> values = new ArrayList<JavaObject>();
		while(paramNum > 0){
			values.add(currentFrame.getOperandStack().pop());
			paramNum--;
		}

		List<JavaObject> params = new ArrayList<JavaObject>();
		for(int i = values.size() - 1; i >= 0; i --){
			params.add(values.get(i));
		}

		nextFrame.setLocalVariableTable(params);
	}
	
}
