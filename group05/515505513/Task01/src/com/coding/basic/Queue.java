package com.coding.basic;

public class Queue {
	private int maxSize;//队列容量
	private Object[] queue;//队列
	private int head;//队列头，可以删除
	private int tail;//队列尾，可以插入
	
	
	public Queue() {
		this(10);
	}

	public Queue(int maxSize) {
		if(maxSize >=0){
			this.maxSize = maxSize;
			this.queue = new Object[maxSize];
			head = tail = 0;
		}else {
			throw new RuntimeException("初始化大小不能小于0");
		}
	}

	public void enQueue(Object o){	
		if(tail == maxSize){
			throw new RuntimeException("队列已满，无法插入新的元素！");
		}else {
			queue[tail++] = o;
		}
	}
	
	public Object deQueue(){
		if(isEmpty()){
			 throw new RuntimeException("空队列异常！");
		}else {
			Object value = queue[head];
			queue[head++] = null;
			return value;
		}
	}
	//队列是否为空
	public boolean isEmpty(){
		return head==tail?true:false;
	}
	
	public int size(){
		return tail-head;
	}
}
