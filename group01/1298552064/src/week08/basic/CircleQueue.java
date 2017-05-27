package week08.basic;

import java.util.Arrays;

/**
 * 用数组实现循环队列
 * @author gallenzhang
 *
 * @param <E>
 */
public class CircleQueue<E> {
	private final static int DEFAULT_SIZE = 10;

	// 用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE];
	
	//队列的长度
	private int size;
	// 队头
	private int front = 0;
	// 队尾
	private int rear = 0;

	public boolean isEmpty() {
		return front == rear;
	}

	public int size() {
		return size;
	}

	public void enQueue(E data) {
		if((rear + 1) % elementData.length == front){
			int len = elementData.length + elementData.length / 2;
			elementData = Arrays.copyOf(elementData, len);
		}
		elementData[rear] = data;
		size++;
		rear = (rear + 1) % elementData.length;
	}

	@SuppressWarnings("unchecked")
	public E deQueue() {
		if(isEmpty()){
			return null;
		}
		
		E e = (E) elementData[front];
		front = (front + 1) % elementData.length;
		size--;
		return e;
	}
}
