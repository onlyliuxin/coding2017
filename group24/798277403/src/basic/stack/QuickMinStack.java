package basic.stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	Stack<Integer> stackNum = new Stack<>();
	Stack<Integer> stackMin = new Stack<>();
	public void push(int data){
		stackNum.push(data);
		if(stackMin.isEmpty() || stackMin.peek()>=data){
			stackMin.push(data);
		}
	}

	public int pop(){
		if(stackNum.size()<=0){
			throw new RuntimeException("The stack is empty!");
		}
		int result = stackNum.pop();
		if(result==stackMin.peek()){
			stackMin.pop();
		}
		return result;
	}

	public int findMin(){
		return stackMin.peek();
	}

}
