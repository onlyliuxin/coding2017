package dataStructure_9_Stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	
	Stack<Integer> stack = new Stack<Integer>();
	Stack<Integer> temp = new Stack<Integer>();
	public void push(int data){
		
		
		if(stack.isEmpty()){
			stack.push(data);
			temp.push(data);
		}else{
			
			if(data<temp.peek()){	
				stack.push(data);
				temp.push(data);
			}else{
				stack.push(data);
			}
		}
	}
	public int pop(){
		checkStackIsEmpty();
		int i = 0;
		if(stack.pop() == temp.peek()){
			temp.pop();
			i = stack.pop();
		}else{
			i = stack.pop();
		}
		return i;
	}
	
	private void checkStackIsEmpty() {
		if(stack.isEmpty()){
			throw new RuntimeException("栈为空");
		}
		
	}
	public int findMin(){
		return temp.peek();
	}
}
