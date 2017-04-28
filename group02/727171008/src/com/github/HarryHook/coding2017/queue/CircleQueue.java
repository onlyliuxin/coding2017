package com.github.HarryHook.coding2017.queue;

import java.util.NoSuchElementException;

/**
 * 用数组实现循环队列
 * 
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue<E> {

    private final static int DEFAULT_SIZE = 10;

    // 用数组来保存循环队列的元素
    private Object[] elementData = new Object[DEFAULT_SIZE];

    // 队头
    private int front = 0;
    // 队尾,最后一个有效元素的下一个元素
    private int rear = 0;
    // 队内元素个数
    private int size = 0;
    public boolean isEmpty() {
	if(rear == front) {  //但不一定为0
	    return true;
	}
	return false;

    }

    public int size() {	
	return size;
    }

    public void enQueue(E data) {
	if((rear + 1) % DEFAULT_SIZE != front) { //队列未满
	    elementData[rear] = data;
	    rear = (rear + 1) % DEFAULT_SIZE;
	    size++;
	}

    }

    public E deQueue() {
	if (isEmpty()) {
	    throw new NoSuchElementException("Queue underflow");
	}
	E data = (E) elementData[front];
	front = (front + 1) % DEFAULT_SIZE;
	size--;
	return data;
    }
    public static void main(String[] args) {
	CircleQueue<Integer> queue = new CircleQueue<>();
	queue.enQueue(1);
	queue.enQueue(1);
	queue.enQueue(2);
	queue.enQueue(3);
	queue.enQueue(1);
	queue.enQueue(1);
	queue.enQueue(2);
	queue.enQueue(3);
	queue.enQueue(1);
	queue.enQueue(1);
	queue.enQueue(2);
	queue.enQueue(3);
	System.out.println("queue.deQueue()= " + queue.deQueue());
	System.out.println("queue.deQueue()= " + queue.deQueue());
	System.out.println("queue.deQueue()= " + queue.deQueue());
	System.out.println("queue.deQueue()= " + queue.deQueue());
	System.out.println("queue.size= " + queue.size());
	queue.enQueue(3);
	queue.enQueue(3);
	queue.enQueue(1);
	queue.enQueue(1);
	queue.enQueue(2);
	System.out.println("queue.rear= " + queue.rear);
	System.out.println("queue.front= " + queue.front);
    }
}
