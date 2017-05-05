package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size = 0;
	public void push(Object o){	
		//入栈在栈顶进入最后压入
		elementData.add(o);
		size ++;
	}
	
	public Object pop(){
		Object object = elementData.get(size -1);
		elementData.remove(size -1);
		size --;
		return object;
	}
	
	public Object peek(){
		Object object = elementData.get(size -1);
		return object;
	}
	public boolean isEmpty(){
		if(size <= 0){
			return true;
		}else{
			return false;
		}
	}
	public int size(){
		return size;
	}

	@Override
	public String toString() {
		return elementData.toString();
	}
	
}
