package com.donaldy.basic.queue;

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
    //元素个数
    private int size = 0;
	
	public boolean isEmpty() {

		return this.size == 0;
        
    }

    public int size() {
        return this.size;
    }

    

    public void enQueue(E data) {

        if (this.size == DEFAULT_SIZE) {
            throw new IndexOutOfBoundsException("size() : " + this.size());
        }

        this.elementData[this.rear] = data;

        this.rear = (this.rear + 1) % DEFAULT_SIZE;

        this.size ++;

    }

    public E deQueue() {

	    if (this.isEmpty()) {
            throw new IndexOutOfBoundsException("size() : " + this.size());
        }

        E oldElement = (E) this.elementData[this.front];

        this.elementData[this.front] = null;

        this.front = (this.front + 1) % DEFAULT_SIZE;

        this.size --;

        return oldElement;
    }

    public E getElement(int index) {

	    if (index < 0 || index >= DEFAULT_SIZE) {
	        throw new IndexOutOfBoundsException("index : " + index);
        }

        return (E)this.elementData[index];
    }
}
