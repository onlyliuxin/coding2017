package com.basic.datastructure;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size;
	
	public void push(Object o){		
		elementData.add(o);
		size++;
	}
	public Object pop(){
		size --;
		return elementData.remove(elementData.size() - 1);
	}
	
	/**
	 * Looks at the object at the top of this stack without removing it from the stack.
	 * @return Object
	 */
	public Object peek(){
		return elementData.get(elementData.size() - 1);
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public int size(){
		return size;
	}
	
	public static void main(String[] args) {
		Stack stack = new Stack();
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		System.out.println(stack.peek());
		
		stack.pop();
		System.out.println("Finished");
	}

}
