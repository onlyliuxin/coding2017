package stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	private Stack<Integer> stack;
	private Stack<Integer> minStack;
	
	public QuickMinStack(){
		stack = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}
	
	public void push(Integer data){
		stack.push(data);
		if(minStack.isEmpty()){
			minStack.push(data);
		}else if(data <= minStack.peek()){
			minStack.push(data);
		}
	}

	public int pop(){
		if(stack.isEmpty()){
			throw new RuntimeException("the stack is empty");
		}
		int value = stack.pop();
		if(minStack.peek() == value){
			minStack.pop();
		}
		return value;
	}
	public int findMin(){
		if(minStack.isEmpty()){
			throw new RuntimeException("the minStack is empty");
		}
		return minStack.peek();
	}
}
