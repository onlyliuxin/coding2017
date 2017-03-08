
public class Queue {
	LinkedList elementData = new LinkedList();

	public void enQueue(Object o) {
		elementData.add(o);
	}

	public Object deQueue() {
		if (elementData.size() == 0) {

		}
		return elementData.removeFirst();
	}

	public boolean isEmpty() {
		return elementData.size() == 0;
	}

	public int size() {
		return elementData.size();
	}
}
