package mini_jvm.engine;

import mini_jvm.method.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExecutorEngine {

	private Stack<StackFrame> stack = new Stack<StackFrame>();
	
	public ExecutorEngine() {
		
	}
	
	public void execute(Method mainMethod){
		
		StackFrame mainFrame = StackFrame.create(mainMethod);
		stack.push(mainFrame);
		
		while(!stack.empty()){
			
			StackFrame frame = stack.peek();			
			
			ExecutionResult result = frame.execute();
			
			if(result.isPauseAndRunNewFrame()){
				
				Method nextMethod = result.getNextMethod();
				StackFrame nextFrame = StackFrame.create(nextMethod);
				nextFrame.setCallerFrame(frame);				
				setupFunctionCallParams(frame,nextFrame);
				
				stack.push(nextFrame);
				
			} else {
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
