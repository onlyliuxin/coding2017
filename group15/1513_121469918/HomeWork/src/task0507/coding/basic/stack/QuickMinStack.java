package task0507.coding.basic.stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	private Stack<Integer> normalNumStack = new Stack<>();
	private Stack<Integer> minNumStack = new Stack<>();
	
	public void push(int data){
		normalNumStack.push(data);
		
		if(minNumStack.isEmpty()){
			minNumStack.push(data);
		}else{
			if(minNumStack.peek()>=data){
				minNumStack.push(data);
			}
		}
	}
	public int pop(){
		if(normalNumStack.isEmpty()){
			throw new RuntimeException("the stack is empty");
		}
		int value = normalNumStack.pop();
		if(value == minNumStack.peek()){
			minNumStack.pop();
		}
		return value;
	}
	public int findMin(){
		return minNumStack.peek();
	}
}
