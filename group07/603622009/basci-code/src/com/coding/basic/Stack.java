package com.coding.basic;

import java.util.Iterator;



public class Stack implements Iterable {
	private ArrayList elementData = new ArrayList();//this is a resizing array, just use it
	
	public Stack(){
		
	}
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		int size = elementData.size(); 
		return elementData.remove(size-1);
	}
	
	public Object peek(int i){
		return elementData.get(i);
	}
	public boolean isEmpty(){
		return elementData.isEmpty();
	}
	public int size(){
		return elementData.size();
	}
	
	public Iterator iterator(){
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator
	{
		private int i = elementData.size();
		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public Object next() {
			return elementData.get(--i);
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
	public static void main(String[] args){
		System.out.println("=======1========");
		Stack stack = new Stack();
		stack.push("123");
		System.out.println(stack.isEmpty());
		System.out.println(stack.size());
		
		System.out.println("=======2========");
		for (int i = 0; i < 200; i++) {
			String tmp = i + "";
			stack.push(tmp);
		}
		System.out.println(stack.isEmpty());
		System.out.println(stack.size());
		
		
		System.out.println("=======3========");
		stack.pop();
		System.out.println(stack.isEmpty());
		System.out.println(stack.size());
		
		System.out.println("=======4========");
		for (int i = 0; i < 150; i++) {
			stack.pop();
		}
		System.out.println(stack.isEmpty());
		System.out.println(stack.size());
		
		System.out.println("=======5========");
		for(Object str:stack){
			System.out.println(str);
		}
		
		System.out.println("=======6========");
		for (int i = 0; i < stack.size(); i++) {
			System.out.println(stack.peek(i));
		}
		
	}
}