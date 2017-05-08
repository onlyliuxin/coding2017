package com.dudy.learn01.data_structure.queue;

import java.util.Random;

/**
 * 用数组实现循环队列
 */
public class CircleQueue<E> {

	private final static int DEFAULT_SIZE = 10;

	// 用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE];

	// 队头
	private int front = 0;
	// 队尾
	private int tear = 0;

	public boolean isEmpty() {
		return tear == front && elementData[0] == null;
	}

	public int size() {
		if(tear == front && elementData[0]!= null){
			return DEFAULT_SIZE;
		} else {
			return (tear - front + DEFAULT_SIZE) % DEFAULT_SIZE;			
		}
	}

	public boolean full(){
		return tear == front && elementData[0] != null;
	}
	
	
	public void enQueue(E data) {
		if(full()){
			System.out.println("队列已满");
		} else {
			elementData[tear] = data;
			tear = (tear + 1) % DEFAULT_SIZE;
		}
	}

	public E deQueue() {
		if(isEmpty()) return null;
		E e = (E) elementData[front];
		elementData[front] = null;
		front = (front + 1) % DEFAULT_SIZE;
		return e;
	}

	public static void main(String[] args) {
		CircleQueue<Integer> queue = new CircleQueue<>();
		Random r = new Random();
		queue.enQueue(1);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.enQueue(6);
		queue.enQueue(7);

		System.out.println(queue.size());
		for (int i = 0; i < 10; i++) {
			queue.enQueue(r.nextInt(100));
		}
		
		System.out.println("size = " + queue.size());


		for(int i=0;i < 11 ; i++){
			System.out.print(queue.deQueue() + ",");
		}
		
		System.out.println("size = " + queue.size());

	}
}