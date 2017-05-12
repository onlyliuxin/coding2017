package com.github.miniyk2012.coding2017.basic.queue;

import java.util.ArrayDeque;

/**
 * 用数组实现循环队列,这也是一个队列，和普通队列有相同的外部特性，只是内部使用循环数组实现的罢了
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	protected int head = 0;
	//队尾  
	protected int rear = 0;   // rear指向的位置不放值，也就是说即使满了，也有一个空位

    public CircleQueue(int capacity) {
        elementData = new Object[capacity+1];  // 实际的数组长度要比容量大1
    }

    public CircleQueue() {
    }

    public boolean isEmpty() {
		return head == rear;
    }

    public int size() {

        return Math.floorMod(rear-head, elementData.length);
    }

    /**
     * 如果满了还往里面放，则抛IndexOutOfBoundsException异常
     */
    public void enQueue(E data) {
        if (isFull()) throw new IndexOutOfBoundsException("循环队列已满");
        elementData[rear] = data;
        rear = Math.floorMod(rear+1, elementData.length);
    }

    /**
     * 如果为空，则抛IndexOutOfBoundsException异常
     * @return
     */
    public E deQueue() {
        if (isEmpty()) throw new IndexOutOfBoundsException("循环队列为空");
        E result = (E) elementData[head];
        head = Math.floorMod(head+1, elementData.length);
        return result;
    }

    public boolean isFull() {
	    return size() == elementData.length-1;
    }

    public static void main(String[] args) {
        /**
         Math.floorMod( 2,  3) =  2
         Math.floorMod(-2,  3) =  1
         Math.floorMod( 2, -3) = -1
         Math.floorMod(-2, -3) = -2
         */
        System.out.println(Math.floorMod(1-2, 5));
    }
}
