package week08.queue;

/**
 * 用数组实现循环队列
 * 
 * @author Hui Zhou
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
		return front == rear && elementData[front] == null;
	}

	public int size() {
		if (isEmpty()) {
			return 0;
		}
		return rear > front ? rear - front : DEFAULT_SIZE - (rear - front);
	}

	public void enQueue(E data) {
		if (this.size() >= DEFAULT_SIZE) {
			throw new RuntimeException("the queue is full.");
		}
		elementData[rear] = data;
		rear = (rear + 1) % DEFAULT_SIZE;
	}

	public E deQueue() {
		if (isEmpty()) {
			throw new RuntimeException("this queue if empty.");
		}
		Object data = elementData[front];
		elementData[front] = null;
		front = (front + 1) % DEFAULT_SIZE;
		return (E) data;
	}
}
