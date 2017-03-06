package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		if(elementData.size()==0)throw new RuntimeException("Stack is empty.");
		return elementData.remove(elementData.size()-1);
	}
	
	public Object peek(){
		if(elementData.size()==0)throw new RuntimeException("Stack is empty.");
		return elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		return (elementData.size()==0);
	}
	public int size(){
		return elementData.size();
	}
	
	@Override
	public String toString(){
		return elementData.toString();
	}
	
	public static void main(String[] args){
		Stack s = new Stack();
		s.push("aaa");
		s.push("bbb");
		s.push("ccc");
		System.out.println(s);
		System.out.println(s.isEmpty());
		System.out.println(s.size());
		System.out.println(s.peek());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s);
		System.out.println(s.isEmpty());
		System.out.println(s.size());
		//System.out.println(s.pop());
	}
}
