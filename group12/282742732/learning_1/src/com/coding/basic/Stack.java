package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		return elementData.remove(elementData.size()-1);
	}
	
	public Object peek(){
		return elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		if (elementData.size()>0) {
			return false;
		}
		return true;
	}
	public int size(){
		return  elementData.size();
	}
	
	public static void main(String[] args) {
		
		Stack st = new Stack();
		st.push("aaa");
		st.push("bbb");
		st.push("ccc");
		System.out.println(st.isEmpty());
		int length = st.size();
		
		for (int i = 0; i < length; i++) {
			System.out.println(st.peek());
		}
		
		System.out.println(st.isEmpty());
	}
}
