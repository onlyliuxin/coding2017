package minijvm.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import minijvm.method.Method;

public class ExecutorEngine {

	private Stack<StackFrame> stack = new Stack<StackFrame>();
	
	public ExecutorEngine() {
		
	}
	
	public void execute(Method mainMethod){
		StackFrame stackFrame = StackFrame.create(mainMethod);
		stack.push(stackFrame);
		
		while (!stack.empty()) {
		    StackFrame frame = stack.peek();
		    ExecutionResult result = frame.execute();
		    
		    if (result.isPauseAndRunNewFrame()) {
		        Method nextMethod = result.getNextMethod();
		        StackFrame nextFrame = StackFrame.create(nextMethod);
		        nextFrame.setCallerFrame(frame);
		        // 当前栈帧将参数传递给下一个栈帧中的局部变量表
		        setupFunctionCallParams(frame, nextFrame);
		        
		        stack.push(nextFrame);
		    } else {
		        stack.pop();
		    }
		}
		
	}
	
	
	
	private void setupFunctionCallParams(StackFrame currentFrame,StackFrame nextFrame) {
	    // 主要用来获取下一个栈帧需要的参数个数
		Method nextMethod = nextFrame.getMethod();
		List<String> parameterList = nextMethod.getParameterList();
		int paramNum = parameterList.size() + 1; // 给this参数增加空间
		
		List<JavaObject> values = new ArrayList<>();
		
		// 将当前栈帧中的操作数栈（参数）传递给下一个栈帧
		for (int i = 0; i < paramNum; i++) {
		    values.add(currentFrame.getOprandStack().pop());
		}
		// 因为弹出的参数顺序与栈帧中的相反，故逆置一下以让参数一一对应
		Collections.reverse(values);
		
		nextFrame.setLocalVariableTable(values);
	}
	
}
