package mini_jvm.engine;

import mini_jvm.method.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * 维护一个函数帧栈
 */
public class ExecutorEngine {

	private Stack<StackFrame> stack = new Stack<StackFrame>();
	
	public ExecutorEngine() {
		
	}
	
	public void execute(Method mainMethod){
		//为Main函数创建一个函数帧
		StackFrame mainFrame = StackFrame.create(mainMethod);
		//将函数帧压入栈中
		stack.push(mainFrame);

		//不断的从函数帧栈中取帧来执行
		while(!stack.isEmpty()){
			StackFrame currentFrame = stack.peek();
			ExecutionResult executionResult = currentFrame.execute();

			if(executionResult.isPauseAndRunNewFrame()){
				//如果函数帧在执行过程中调用了其他的方法，则开启一个新的函数帧，并压入栈中
				Method nextMethod = executionResult.getNextMethod();
				StackFrame nextFrame = StackFrame.create(nextMethod);

				//为新的函数帧设置回调
				nextFrame.setCallerFrame(currentFrame);
				//将参数传递给新的函数
				setupFunctionCallParams(currentFrame,nextFrame);

				stack.push(nextFrame);
			}else{
				//否则此函数帧执行完毕，弹出
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

		//把当前函数帧操作数栈的前几个值复制给新的函数帧，相当于传递方法的参数
		//数据结构知识：从栈中取出栈顶的x个元素
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
