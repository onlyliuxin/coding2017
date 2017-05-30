package com.coding.basic.stack;


/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author zj
 * @param <T>
 *
 */
public class QuickMinStack<T extends Comparable<T>> {
	

	private Stack<T> dataStack = new Stack<T>();
	
	private Stack<T> minStack = new Stack<T>();
	
	public void push(T item){
					
		if(minStack.isEmpty()||item.compareTo(minStack.peek())<0){
			minStack.push(item);
		}else{
			minStack.push(minStack.peek());
		}
		
		dataStack.push(item);
	}

	public T pop(){
		
		minStack.pop();
		return dataStack.pop();
	}
	
	public T findMin(){
		
		return minStack.peek();
	}
	
}
