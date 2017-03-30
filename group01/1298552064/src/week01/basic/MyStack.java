package week01.basic;

public class MyStack {
	private MyArrayList elementData = new MyArrayList();

	public void push(Object o) {
		elementData.add(o);
	}

	public Object pop() {
		return elementData.remove(size() - 1);
	}

	public Object peek() {
		return elementData.get(size() - 1);
	}

	public boolean isEmpty() {
		return elementData.size() == 0;
	}

	public int size() {
		return elementData.size();
	}
}
