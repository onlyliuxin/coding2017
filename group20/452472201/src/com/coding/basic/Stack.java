package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size;
	public void push(Object o){	
		elementData.add(o);
		size++;
	}

	public Object pop(){
		if(elementData==null){
			try {
				throw new Exception();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return elementData.remove(size-1);

	}

	public Object peek(){
		return elementData.get(size() - 1);
	}

	public boolean isEmpty(){
		return size==0;
	}
	public int size(){
		return size;
	}
}
