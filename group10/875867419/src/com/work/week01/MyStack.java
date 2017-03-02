package com.work.week01;

public class MyStack<E> {
	private MyArrayList<E> elementData;
	
	public MyStack(){
		elementData = new MyArrayList<>();
	}
	
	public void push(E element){
		elementData.add(element);
	}
	
	public E pop(){		//移除栈顶元素	后进先出
		return elementData.remove(elementData.size() - 1);
	}
	
	public E peek(){	//获取栈顶元素
		return elementData.get(elementData.size() - 1);
	}
	
	public int size(){
		return elementData.size();
	}
	
	public boolean isEmpty(){
		return elementData.isEmpty();
	}
	
	public static void main(String[] args) {
		MyStack<String> stack = new MyStack<String>();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		stack.push("5");
		System.out.println("size="+stack.size());
		System.out.println("peek栈顶元素="+stack.peek());
		while(!stack.isEmpty()){
			System.out.println("pop栈顶元素"+stack.pop());
		}
	}
}
