package assignment;

public class MyStack<T> {
	private MyArrayList<T> elementData = new MyArrayList<>();

	public void push(T o) {
		elementData.add(o);
	}

	public T pop() {
		if (!isEmpty()) {
			T data = elementData.remove(elementData.size() - 1);
			return data;
		}
		throw new StackIsEmptyException();
	}

	public T peek() {
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
