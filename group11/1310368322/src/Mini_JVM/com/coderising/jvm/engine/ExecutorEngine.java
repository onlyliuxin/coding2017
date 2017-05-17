package com.coderising.jvm.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;



import com.coderising.jvm.method.Method;

public class ExecutorEngine {
	
	private Stack<StackFrame> stack = new Stack<StackFrame>();
	List list = new ArrayList();
	public ExecutorEngine(){
		
	}
	
	public void execute(Method mainMethod){
		
		// 生成 main方法的栈帧
		StackFrame mainFrame = StackFrame.create(mainMethod);
		stack.push(mainFrame);
		
		
		while(! stack.isEmpty() ){
			
			StackFrame frame = stack.peek();
			
			ExecutionResult result = frame.execute();
			
			if(result.isPauseAndRunNewFrame()){
				
				Method nextMethod = result.getNextMethod();
				StackFrame nextFrame = StackFrame.create(nextMethod);// 创建新的栈帧
				nextFrame.setCallerFrame(frame);
				setupFunctionCallParams(frame, nextFrame);
				
			} else {
				stack.pop();
			}
		}
		
	}
	
	private void setupFunctionCallParams(StackFrame currentFrame,StackFrame nextFrame) {
		
		Method nextMethod = nextFrame.getMethod();
		
		List<String> paramList = nextMethod.getParameterList();
		// 加上 1 是因为要把 this 也传过去
		int paramNum = paramList.size() + 1;
		
		List<JavaObject> values = new ArrayList<JavaObject>();
		// 从栈中取出 栈顶的 X 个元素
		while(paramNum > 0){
			values.add(currentFrame.getOprandStack().pop());
			paramNum--;
		}
		
		// 把一个列表倒序排列
		List<JavaObject> params = new ArrayList<JavaObject>();
		for(int i = values.size() -1; i>=0; i--){
			params.add(values.get(i));
		}
		
		nextFrame.setLocalVariableTable(params);
		
	}
}
