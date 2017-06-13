package main.week01.data_structure;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size = 0;

	public void push(Object o) {
		elementData.add(size++,o);
	}

	public Object pop() {
		Object temp = elementData.get(size-1);
		elementData.remove(size---1);
		return temp;
	}

	public Object peek() {
		return elementData.get(size-1);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
}
