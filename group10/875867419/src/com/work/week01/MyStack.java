package com.work.week01;

public class MyStack<E> {
	private MyArrayList<E> elementData;
	
	public MyStack(){
		elementData = new MyArrayList<>();
	}
	
	public void push(E element){
		elementData.add(element);
	}
	
	public E pop(){		//�Ƴ�ջ��Ԫ��	����ȳ�
		return elementData.remove(elementData.size() - 1);
	}
	
	public E peek(){	//��ȡջ��Ԫ��
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
		System.out.println("peekջ��Ԫ��="+stack.peek());
		while(!stack.isEmpty()){
			System.out.println("popջ��Ԫ��"+stack.pop());
		}
	}
}
