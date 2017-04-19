package Collection;

public class MyQueue<T> {
	private MyLinkedList<T> elementData = new MyLinkedList<>();

	public void enQueue(T o) {
		elementData.addLast(o);
	}

	public T deQueue() {
		if (!isEmpty()) {
			return elementData.remove(0);
		}
		throw new QueueIsEmptyException();
	}

	public boolean isEmpty() {
		return elementData.size() == 0;
	}

	public int size() {
		return elementData.size();
	}
}

class QueueIsEmptyException extends RuntimeException {
	public QueueIsEmptyException() {
		super();
	}

	public QueueIsEmptyException(String string) {
		super(string);
	}
}