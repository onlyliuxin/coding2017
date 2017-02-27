package com.work.week01;

public class MyQueue<E> {
	private MyArrayList<E> elementData;
	
	public MyQueue(){
		elementData = new MyArrayList<E>();
	}
	
	public void enQueue(E element){//入队
		elementData.add(element);
	}
	
	public E deQuene(){//出队		先进先出
		return elementData.remove(0);
	}
	
	public int size(){
		return elementData.size();
	}
	
	public boolean isEmpty(){
		return elementData.isEmpty();
	}
	
	public static void main(String[] args) {
		MyQueue<String> queue = new MyQueue<String>();
		queue.enQueue("1");
		queue.enQueue("2");
		queue.enQueue("3");
		queue.enQueue("4");
		queue.enQueue("5");
		System.out.println("size="+queue.size());
		while(!queue.isEmpty()){
			System.out.println(queue.deQuene());
		}
	}
}
