package com.coding.mybasic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		checkEmptyStack();
		return elementData.remove(size() - 1);
	}
	
	public Object peek(){
		checkEmptyStack();
		Object element = elementData.get(size() - 1);
		return element;
	}

	public boolean isEmpty(){
		return size() <= 0;
	}
	public int size(){
		return elementData.size();
	}
	/**
	 * 检查栈是否为空
	 */
	private void checkEmptyStack() {
		if(isEmpty()){
			throw new RuntimeException("size:" + size() + " 空栈");
		}
	}
}
