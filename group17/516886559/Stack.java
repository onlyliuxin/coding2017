package com.rd.p2p.common.util.liuxin;

public class Stack<T> {
	
	private ArrayList<T> elementData = new ArrayList<T>();
	
	public void push(T o){		
		elementData.add(o);
	}
	
	public T pop(){
		if(elementData.size() > 0){
			@SuppressWarnings("unchecked")
			T obj = (T)elementData.get(elementData.size()-1);
			elementData.remove(elementData.size()-1);
			return obj;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T peek(){
		if(elementData.size() > 0){
			return (T)elementData.get(elementData.size()-1);
		}else{
			return null;
		}
	}
	public boolean isEmpty(){
		return elementData.size() == 0 ? true : false;
	}
	public int size(){
		return elementData.size();
	}
	
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		System.out.println(stack.pop());
		System.out.println(stack.peek());
		System.out.println(stack.peek());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.peek());
	}
}

