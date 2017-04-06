package datastructure.basic;

import datastructure.exception.EmptyQueueException;

public class Queue {
	//数组实现自增长的循环队列
	private Object[] array;
	private int head = 0;
	private int rear = 0;

	public Queue() {
	    this.array = new Object[10];
    }

	public Queue(int initCapacity) {
	    this.array = new Object[initCapacity];
    }

	public void enQueue(Object o) {
		int target = mapIndex(rear);
		autoGrow();
		array[target] = o;
		rear++;
	}
	
	public Object deQueue() {
	    if (isEmpty()) {
	        throw new EmptyQueueException();
        }
		Object obj = array[mapIndex(head)];
		head++;
		return obj;
	}

    public boolean isEmpty() {
		return head == rear;
	}
	
	public int size() {
		return rear - head;
	}

	private int capacity() {
		return array.length;
	}

	private void autoGrow() {
		if (size() >= capacity()) {
			Object[] newArray = new Object[nextCapacity()];
			System.arraycopy(array, 0, newArray, 0, capacity());

			int increase = nextCapacity() - capacity();
			int moveCount = size() - mapIndex(rear);
			System.arraycopy(newArray, mapIndex(head), newArray, mapIndex(head) + increase, moveCount);
			array = newArray;
			head += increase;
			rear += increase;
		}
	}

	private int nextCapacity() {
		return capacity() * 2;
	}

	private int mapIndex(int index) {
		return index >= capacity() ? index % capacity() : index;
	}
}
