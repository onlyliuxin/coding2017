package week9;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 *
 */
public class QuickMinStack {
	
	private Stack<Integer> normalStack=new Stack<Integer>();
	private Stack<Integer> minDataStack=new Stack<Integer>();
	
	public void push(int data){
		
		normalStack.push(data);
		
		if(!minDataStack.isEmpty()){
			
			if(data <= minDataStack.peek()){
				minDataStack.push(data);
			}
			
		}else{
			minDataStack.push(data);
		}
	}
	
	public int pop(){
		if(normalStack.isEmpty()){
			throw new RuntimeException("The Stack is Empty,can't execute pop");
		}
		
		int data=normalStack.pop();		
		if(minDataStack.peek() == data){
			minDataStack.pop();
		}
		
		return data;
	}
	
	public int findMin(){
		if(minDataStack.isEmpty()){
			throw new RuntimeException("The Stack is Empty,can't execute findMin");
		}
		
		return minDataStack.peek();
	}
}










