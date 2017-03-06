package com.coding.basic;

public class Stack {
	
	private ArrayList elementData = new ArrayList();
	private int size;
	public void push(Object o){	
		elementData.add(o);
		size++;
	}
	
	public Object pop(){
		return elementData.get(size-1);
	}
	
	public Object peek(){
		elementData.remove(size--);
		return elementData;
	}
	public boolean isEmpty(){
		return size==0;
	}
	public int size(){
		return size;
	}
public static void main(String args[]){
	Stack s = new Stack();
	s.push("1");
	s.push("2");
	s.push("3");
	s.push("4");
	System.out.println(s.pop());
	s.peek();
	for(int i =0;i<s.size();i++){
	System.out.println(s.elementData.get(i));}
}
}

