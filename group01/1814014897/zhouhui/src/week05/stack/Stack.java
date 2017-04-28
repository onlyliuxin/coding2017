package week05.stack;

import week01.datastructure.ArrayList;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size = 0;
	
	public void push(Object o){		
		elementData.add(o);
		size++;
	}
	
	public Object pop(){
		return elementData.remove(--size);
	}
	
	public Object peek(){
		return elementData.get(size - 1);
	}
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
