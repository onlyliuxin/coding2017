package assignment;

//
public class MyQueue {
	private MyLinkedList elementData = new MyLinkedList();

	public void enQueue(Object o) {
		elementData.addLast(o);
	}

	public Object deQueue() {
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
