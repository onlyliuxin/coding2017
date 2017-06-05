package com.coding.basic.queue;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	private int len;
	
	public CircleQueue() {
		len = CircleQueue.DEFAULT_SIZE;
	}
	public CircleQueue(int len) {
		this.len = len;
	}
	
	public boolean isEmpty() {
		if(front == rear){
			return true;
		}
		return false;
    }

    public int size() {
        return (rear + len - 1)%len;
    }

    /**
     * 判断满了 预留一个空位
     * @return
     */
    private boolean isFull(){
    	if(front == (rear+1) % len){
    		return true;
    	}
    	return false;
    }

    public void enQueue(E data) {
        if(isFull()){
        	throw new RuntimeException("circle queue is Full");
        }else{
        	elementData[rear] = data;
        	//逻辑上实现首尾相连，循环队列
        	rear = (rear + 1) % len;
        }
    }

    public E deQueue() {
    	if(isEmpty()){
    		throw new RuntimeException("circle queue is Empty");
    	}else{
    		Object e = elementData[front];
    		//头结点不断追赶尾结点
    		front = (front+1)% len;
    		return (E) e;
    	}
    }
    
    public static void main(String[] args) {
    	CircleQueue<Integer> queue = new CircleQueue<Integer>(15);
    	
    	queue.enQueue(1);
    	queue.enQueue(2);
    	queue.enQueue(3);
    	queue.enQueue(4);
    	queue.enQueue(5);
    	queue.enQueue(6);
    	queue.enQueue(7);
    	queue.enQueue(8);
    	queue.enQueue(9);
    	queue.enQueue(10);
    	while(!queue.isEmpty()){
    		System.out.println(queue.deQueue());
    	}
	}
}