package com.coderising.jvm.engine;

import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.method.Method;
import com.coding.basic.stack.Stack;

public class ExecutorEngine {

	private Stack<StackFrame> stack = new Stack<StackFrame>();
	
	public ExecutorEngine() {
		
	}
	
	public void execute(Method mainMethod){
		
		//为main函数创建一个栈帧
		StackFrame mainFrame = StackFrame.create(mainMethod);
		//main函数帧压栈
		stack.push(mainFrame);
		
		while(!stack.isEmpty()){
			
			StackFrame frame = stack.peek();
			ExecutionResult result = frame.execute();
			
			//判断执行结果是否暂停或者有新的函数帧生成
			if(result.isPauseAndRunNewFrame()){
				
				Method method = result.getNextMethod();
				StackFrame nextFrame = StackFrame.create(method);
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
		List<String> paramList = nextMethod.getParameterList();
		
		//加上1 是因为要把this也传递过去		
		int paramNum = paramList.size() + 1;				
		List<JavaObject> values = new ArrayList<JavaObject>();		
		//数据结构知识：  从栈中取出栈顶的x个元素
		while(paramNum>0){			
			values.add(currentFrame.getOprandStack().pop());
			paramNum --;
		}
		//数据结构知识：  把一个列表倒序排列
		List<JavaObject> params = new ArrayList<JavaObject>();
		
		for(int i=values.size()-1; i>=0 ;i--){
			params.add(values.get(i));
		}				
		nextFrame.setLocalVariableTable(params);
	}
	
}
