package datastructure.basic;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o) {
		elementData.add(o);
	}
	
	public Object pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		Object peek = peek();
		elementData.remove(elementData.size() - 1);
		return peek;
	}

	public Object peek() {
		return elementData.get(elementData.size() - 1);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return elementData.size();
	}

	public void clear() {
		elementData = new ArrayList();
	}
}
