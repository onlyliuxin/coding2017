package assignment;

public class Stack<T> {
	private MyArrayList<T> elementData = new MyArrayList<>();

	public void push(T o) {
		elementData.add(o);
	}

	public T pop() {
		if (!isEmpty()) {
			T data = elementData.remove(elementData.size() - 1);
			return data;
		}
		throw new EmptyStackException();
	}

	public T peek() {
		if(!isEmpty())
			return elementData.get(elementData.size() - 1);
		throw new EmptyStackException();
	}

	public boolean isEmpty() {
		return elementData.size() == 0;
	}

	public int size() {
		return elementData.size();
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = elementData.size()-1; i >=0; i--) {
			stringBuilder.append(elementData.get(i));
			if (i != 0) {
				stringBuilder.append(", ");
			}
		}
		return stringBuilder.toString();
	}
}

class EmptyStackException extends RuntimeException {
	public EmptyStackException() {
		super();
	}

	public EmptyStackException(String string) {
		super(string);
	}
}
