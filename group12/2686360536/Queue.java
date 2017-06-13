package org.dul.CodingTask.homework_2017_2_25;

public class Queue {
	private ArrayList elements = new ArrayList();

	public void enQueue(Object object) {
		elements.add(object);
	}

	public Object deQueue() {
		if (isEmpty())
			return null;
		else {
			return elements.remove(elements.size() - 1);
		}
	}

	public boolean isEmpty() {
		if (elements.size() == 0)
			return true;
		else
			return false;
	}

	public int size() {
		return elements.size();
	}
}
