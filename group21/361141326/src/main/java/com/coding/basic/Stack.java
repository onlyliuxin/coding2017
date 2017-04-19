package com.coding.basic;

public class Stack<T> {

	private int size;

	private List<T> elementData;

	public Stack() {
	    elementData = new ArrayList<>();
	}

	public void push(T t){
	    elementData.add(t);
		size ++;
	}
	
	public T pop(){
		return elementData.remove(-- size);
	}
	
	public T peek(){
		return elementData.get(size - 1);
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public int size(){
		return size;
	}
}
