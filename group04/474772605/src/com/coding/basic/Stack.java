package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	int size =0;
	
	public void push(Object o){		//入栈
		elementData.add(o);
		size++;
	}
	
	public Object pop(){ //移走栈顶对象，将该对象作为函数值返回
		Object top = elementData.get(size);
		elementData.remove(size);
		size--;
		return top;
	}
	
	public Object peek(){//查找栈顶对象，而不从栈中移走
		return elementData.get(size);
	}
	public boolean isEmpty(){
		if(this.size ==0){
			return true;
		}
		return false;
	}
	public int size(){
		return this.size;
	}
}
