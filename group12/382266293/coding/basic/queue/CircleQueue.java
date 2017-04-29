package queue;

/**
 * 用数组实现循环队列
 * 
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue<E> {

	private final static int DEFAULT_SIZE = 11;

	// 用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE];

	// 队头
	private int front = 0;
	// 队尾
	private int rear = 0;

	public boolean isEmpty() {
		return size() == 0;

	}

	public int size() {
		return getArrIndex(front - rear);
	}

	private int getArrIndex(int i) {
		return i % DEFAULT_SIZE;
	}

	public void enQueue(E data) {
		checkCapacity();
		elementData[getArrIndex(front)] = data;
		front++;
	}

	private void checkCapacity() {
		if (getArrIndex(front - rear + 1) == 0) {
			throw new RuntimeException("Queue is full");
		}
	}

	public E deQueue() {
		if (size() == 0) {
			return null;
		}
		Object o = elementData[getArrIndex(rear)];
		rear++;
		return (E) o;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size(); i++) {
			sb.append(elementData[getArrIndex(rear + i)]);
			if (i != size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
