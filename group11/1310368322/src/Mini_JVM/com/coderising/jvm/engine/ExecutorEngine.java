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
		
		// ���� main������ջ֡
		StackFrame mainFrame = StackFrame.create(mainMethod);
		stack.push(mainFrame);
		
		
		while(! stack.isEmpty() ){
			
			StackFrame frame = stack.peek();
			
			ExecutionResult result = frame.execute();
			
			if(result.isPauseAndRunNewFrame()){
				
				Method nextMethod = result.getNextMethod();
				StackFrame nextFrame = StackFrame.create(nextMethod);// �����µ�ջ֡
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
		// ���� 1 ����ΪҪ�� this Ҳ����ȥ
		int paramNum = paramList.size() + 1;
		
		List<JavaObject> values = new ArrayList<JavaObject>();
		// ��ջ��ȡ�� ջ���� X ��Ԫ��
		while(paramNum > 0){
			values.add(currentFrame.getOprandStack().pop());
			paramNum--;
		}
		
		// ��һ���б�������
		List<JavaObject> params = new ArrayList<JavaObject>();
		for(int i = values.size() -1; i>=0; i--){
			params.add(values.get(i));
		}
		
		nextFrame.setLocalVariableTable(params);
		
	}
}
