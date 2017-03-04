package org.learning.container;

public class Stack {

	private org.learning.container.LinkedList linkedList = new LinkedList();
	
	
	public void push(Object obj){
		linkedList.add(obj);
	}
	
	public Object pop(){
		Object obj = linkedList.getLast();
		linkedList.removeLast();
		return obj;
	}
	public boolean isEmpty(){
		return (linkedList == null || linkedList.size() == 0) ? true:false; 
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//先进后出
		org.learning.container.Stack stack = new Stack();
		stack.push("1");
		stack.push("2");
		
		Object obj = stack.pop();
		print(obj);
		Object obj2 = stack.pop();
		print(obj2);
		
		print(stack.isEmpty());
		
	}
	
	public static void print(Object obj){
		System.out.println(""+obj);
	}

}
