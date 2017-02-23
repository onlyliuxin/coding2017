package assignment;

//
public class MyStack {
	private MyArrayList elementData = new MyArrayList();

	public void push(Object o) {
		elementData.add(o);
	}

	public Object pop() {
		if (!isEmpty()) {
			Object data = elementData.remove(elementData.size() - 1);
			return data;
		}
		throw new StackIsEmptyException();
	}

	public Object peek() {
		return elementData.get(elementData.size() - 1);
	}

	public boolean isEmpty() {
		return elementData.size() == 0;
	}

	public int size() {
		return elementData.size();
	}
}

class StackIsEmptyException extends RuntimeException {
	public StackIsEmptyException() {
		super();
	}

	public StackIsEmptyException(String string) {
		super(string);
	}
}
