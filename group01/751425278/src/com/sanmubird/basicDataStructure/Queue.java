package com.sanmubird.basicDataStructure;

public class Queue {
	
	
	/*  采用数组的方式实现队列
	 * 	队列的特点是：先进先出，只能从尾进，（不能插队）；只能从头出；
	private Object[] queArray ; //队列
	private int maxSize ; //队列 长度；
	private int front ; //队头，允许删除
	private int rear ; //队尾；允许插入
	
public Queue(int initialSize){
	if(initialSize > 0){
		this.queArray = new Object[initialSize];
		this.maxSize = initialSize;
		this.front = this.rear = 0 ;
	}else{
		throw new RuntimeException("初始化的大小不能小于0；"+ initialSize);
	}
}	

	public void enQueue(Object o){		
		if(rear == maxSize){
			throw new RuntimeException("队列已满，无法插入新的元素！");
		}else{
			queArray[rear++] = o ; 
		}
	}
	
	public Object deQueue(){
		if(isEmpty()){
			throw new RuntimeException("空队列异常！");
		}else{
			Object obj = (Object) queArray[front];
			queArray[front++] = null;
			return obj;
		}
	}
	
	//判空
	public boolean isEmpty(){
		return rear == front?true:false;
	}
	
	public int size(){
		return rear-front;
	}
	*/
	
	
	/**	采用链表实现对队列；队列的特点是：先进先出，而且不能插队；所以只能从尾进，从头出。
	 * 
	 * */
	private LinkedList ll ;
	
	public Queue(){
		this.ll = new LinkedList();
	}
	
	public void enQueue(Object o){
		ll.addLast(o);
	}
	
	public Object deQueue(){
		return ll.removeLast();
	}
	
	public boolean isEmpty(){
		return ll.getHead().next == null ? true: false;
	}
	
	public int size(){
		return ll.size();
	}
}