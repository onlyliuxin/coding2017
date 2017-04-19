package com.coding.basic;

import org.junit.Test;

public class Queue {

	private Object[] objArray;
	private int front=0;//队列头指针
	private int rear=-1;//队列尾指针
	private int maxSize;//队列最大长度
	private int size=0;//队列长度
	public Queue(int maxSize){
		this.maxSize = maxSize;
		this.objArray= new Object[maxSize];
	}
	public Queue(){}
	public void enQueue(Object obj){
		if (rear == maxSize-1)
			rear = -1;
		objArray[++rear] = obj;
		size++;
	}
	
	public Object deQueue(){
		if (front == maxSize)
			front = 0;
		size--;
		return objArray[front++];
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public int size(){
		return size;
	}
	@Test
	public void test(){
		//单元测试时，需要注释掉带参数构造函数
		Queue queue = new Queue();
		queue.enQueue("lisi");
		queue.enQueue("zhangsan");
		queue.enQueue("wangwu");
		queue.enQueue("zhaoliu");
		for (int i=0;i<4;i++) {
			String name = (String)queue.deQueue();
			System.out.println(name);
		}

	}
}