package com.tiaozaoj;

public class NewStack {
	private NewArrayList elementData = new NewArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		Object o = elementData.get(elementData.size());
		elementData.remove(elementData.size());
		return o;
	}
	
	//只获取栈顶元素
	public Object peek(){
		return elementData.get(elementData.size());
	}
	
	public boolean isEmpty(){
		return elementData.size()>0?false:true;
	}
}
