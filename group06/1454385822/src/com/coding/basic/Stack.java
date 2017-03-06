package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int num = 0;
	
	public void push(Object o){
		elementData.add(o);
		num++;
	}
	
	public Object pop(){
	
		return elementData.remove(--num) ;
	}
	
	public Object peek(){
		return elementData.get(num - 1);
	}
	public boolean isEmpty(){
		return num <= 0 ;
	}
	public int size(){
		return num;
	}
	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		System.out.println(stack.peek());
		System.out.println(stack.size());
//		while(!stack.isEmpty()){
//			System.out.println(stack.pop());
//		}
	}
}




