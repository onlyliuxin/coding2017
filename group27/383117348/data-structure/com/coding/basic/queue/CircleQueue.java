package com.coding.basic.queue;

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
	// 队尾
	private int rear = 0;

	public boolean isEmpty() {

		return rear == front && elementData[rear] == null;
	}

	public int size() {
		if (isEmpty()) {
			return 0;
		}
		return rear > front ? rear - front : DEFAULT_SIZE - (front - rear);
	}

	public void enQueue(E data) {
		if (rear == front && elementData[front] != null) {
			throw new IndexOutOfBoundsException("队列已满的异常");
		}
		elementData[rear++] = data;
		// 如果rear已经到头，那就转头
		rear = rear == DEFAULT_SIZE ? 0 : rear;
	}

	@SuppressWarnings("unchecked")
	public E deQueue() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("空队列异常");
		}
		// 保留队列的rear端的元素的值
		E oldValue = (E) elementData[front];
		// 释放队列的rear端的元素
		elementData[front++] = null;
		// 如果front已经到头，那就转头
		front = front == DEFAULT_SIZE ? 0 : front;
		return oldValue;
	}

	public String toString() {
		if (isEmpty()) {
			return "[]";
		} else {
			// 如果front < rear，有效元素就是front到rear之间的元素
			if (front < rear) {
				StringBuilder sb = new StringBuilder("[");
				for (int i = front; i < rear; i++) {
					sb.append(elementData[i].toString() + ", ");
				}
				int len = sb.length();
				return sb.delete(len - 2, len).append("]").toString();
			}
			// 如果front >= rear，有效元素为front->capacity之间、0->front之间的
			else {
				StringBuilder sb = new StringBuilder("[");
				for (int i = front; i < DEFAULT_SIZE; i++) {
					sb.append(elementData[i].toString() + ", ");
				}
				for (int i = 0; i < rear; i++) {
					sb.append(elementData[i].toString() + ", ");
				}
				int len = sb.length();
				return sb.delete(len - 2, len).append("]").toString();
			}
		}
	}
}
