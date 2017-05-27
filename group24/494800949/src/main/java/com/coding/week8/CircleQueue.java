package com.coding.week8;

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

    private int size;
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	public boolean isEmpty() {
		return size == 0;
    }

    public int size() {
        return size;
    }

    

    public void enQueue(E data) {
        if (data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
        elementData[rear] = data;
        size++;
        if ((rear = (rear + 1) % elementData.length) == front) {
            doubleCapacity();
        }
    }

    private void doubleCapacity() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity << 1;
        Object[] elementDataNew = new Object[newCapacity];
        if (newCapacity < 0) {
            throw new RuntimeException("capacitity is too big");
        }
        System.arraycopy(elementData, rear, elementDataNew, 0, oldCapacity - rear );
        System.arraycopy(elementData, 0, elementDataNew, front, front );
        elementData = elementDataNew;
        front = 0;
        rear = oldCapacity;
    }

    public E deQueue() {
        if (size == 0)
            return null;
        @SuppressWarnings("unchecked")
        E e = (E)elementData[front];
        //let gc work
        elementData[front] = null;
        //下标越界处理
        front = ++front % elementData.length;
        size--;
        return e;
    }

}
