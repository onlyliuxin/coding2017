package com.danny.hw1;

public class Stack {
	private ArrayList elementData = new ArrayList();

	public void push(Object o){
		this.elementData.add(o);

	}

	public Object pop(){		
		int size = this.elementData.size();

		if ( size > 0 ) {
			return this.elementData.remove(size - 1 );
		} else{
			throw new IndexOutOfBoundsException("Stack is empty");
		}
	}

	public Object peek(){

		if ( size() > 0 ) {
			return this.elementData.get(size() - 1 );
		} else{
			throw new IndexOutOfBoundsException("Stack is empty");
		}
	}
	public boolean isEmpty(){
		return size() == 0;
	}

	public int size(){
		return this.elementData.size();
	}

	public Stack() {
		// TODO Auto-generated constructor stub
	}
}
