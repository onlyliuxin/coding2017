package main;

public class Queue {
	private LinkedList list = new LinkedList();

	public void enQueue(Object o) {
		list.add(o);
	}

	public Object deQueue() {
		return list.remove(0);
	}

	public boolean isEmpty() {
		return list.size() == 0;
	}

	public int size() {
		return list.size();
	}

}
