package week8.jvm.engine;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import week8.jvm.method.Method;

public class ExecutorEngine {

	private Stack<StackFrame> stack=new Stack<>();
	
	public void execute(Method mainMethod) {
		
		StackFrame mainFrame=StackFrame.createFrame(mainMethod);
		stack.push(mainFrame);
		
		while(!stack.isEmpty()){
			StackFrame currentFrame=stack.peek();
			
			ExecutorResult result=currentFrame.execute();
			
			if(result.isPauseAndRunNewFrame()){
				
				Method nextMethod=result.getNextMethod();
				
				StackFrame nextStackFrame=StackFrame.createFrame(nextMethod);
				nextStackFrame.setCallerFrame(currentFrame);
				
				setupFunctionCallParams(currentFrame,nextStackFrame);
				
				stack.push(nextStackFrame);
				
			}else{
				stack.pop();
			}
		}
		
	}

	private void setupFunctionCallParams(StackFrame currentFrame,
			StackFrame nextStackFrame) {
		
		Method nextMethod=nextStackFrame.getMethod();
		
		List<String> paramList=nextMethod.getParameterList();
		
		////加上1 是因为要把this也传递过去
		int paramNum=paramList.size() + 1;
		
		List<JavaObject> values=new ArrayList<>();
		List<JavaObject> params=new ArrayList<>();
		
		while(paramNum>0){		
			values.add(currentFrame.getOperandStack().pop());
			paramNum--;
		}
		
		//链表反转
		for(int i=values.size()-1;i>=0;i--){
			params.add(values.get(i));
		}
		
		//为局部变量表赋值
		nextStackFrame.setLocalVariableTable(params);
		
	}

}
