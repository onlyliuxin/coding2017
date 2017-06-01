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
		
		while(!stack.isEmpty()){
			StackFrame currentFrame = stack.peek();
			ExecutionResult result = currentFrame.execute();
			if(result.isPauseAndRunNewFrame()){
				Method nextMethod = result.getNextMethod();
				StackFrame nextFrame = StackFrame.create(nextMethod);
				nextFrame.setCallerFrame(currentFrame);
				setupFunctionCallParams(currentFrame, nextFrame);
				stack.push(nextFrame);
			}else{
				stack.pop();
			}
		}
		
		
	}
	
	/*
	 * This method should be looking for the parameter list from the next frame.
	 * 
	 */
	private void setupFunctionCallParams(StackFrame currentFrame,StackFrame nextFrame) {
		System.out.println("Going to setup function parameters for next frame");
		
		List<String> params = nextFrame.getMethod().getParameterList();
		params.forEach(System.out::println);
		//List<JavaObject> paramObjForNextframe = new ArrayList<>();
		Stack<JavaObject> tmpCache = new Stack<>();
		int paramSize = params.size() + 1;
		while(paramSize -- > 0){
			JavaObject jo = currentFrame.getOprandStack().pop();
			tmpCache.push(jo);
		}
		while(!tmpCache.isEmpty()){
			nextFrame.addLocalVariableValue(tmpCache.pop());
		}
		//System.out.println("size of next frame local variable table: " + nextFrame.getLocalVariableTable().size());
		//nextFrame.getLocalVariableTable().forEach(System.out::println);
		
	}
	
}
