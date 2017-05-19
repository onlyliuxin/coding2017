package week09.basic;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author gallenzhang
 *
 */
public class QuickMinStack {
	Stack<Integer> stack = new Stack<>();
	Stack<Integer> minStack = new Stack<>();
	
	public void push(int data) {
		stack.push(data);
		if(minStack.isEmpty()){
			minStack.push(data);
			return ;
		}
		
		if(minStack.peek() > data){
			minStack.push(data);
		}
	}

	public int pop() {
		if(stack.isEmpty()){
			throw new EmptyStackException();
		}
		
		int item = stack.pop();
		if(item == minStack.peek()){
			minStack.pop();
		}
		return item;
	}

	public int findMin() {
		if(minStack.isEmpty()){
			throw new EmptyStackException();
		}
		return minStack.peek();
	}
	
}
