package assignment;

//
import java.util.Arrays;

public class MyArrayList {
	private Object[] elementData;
	private static final int DEFAULT_SIZE = 10;
	private int size;

	public MyArrayList() {
		this(DEFAULT_SIZE);
	}

	public MyArrayList(int initSize) {
		if (initSize <= DEFAULT_SIZE) {
			elementData = new Object[DEFAULT_SIZE];
		}
		else {
			elementData = new Object[initSize];
		}
		size = 0;
	}

	public void add(Object o) {
		growIfNeed();
		elementData[size++] = o;
	}

	public void add(int index, Object o) {
		growIfNeed();
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		rangeCheck(index);
		return elementData[index];
	}

	public Object remove(int index) {
		rangeCheck(index);
		Object target = get(index);
		// moveForwardFrom(index + 1);
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		size--;
		return target;
	}

	public int size() {
		return size;
	}

	private void rangeCheck(int index) {
		if (index >= size) {
			throw new NoElementException("index:" + index);
		}
	}

	private void growIfNeed() {
		if (size == elementData.length) grow();
	}

	private void grow() {
		elementData = Arrays.copyOf(elementData, elementData.length * 2);
	}

	private void moveBackwardFrom(int index) {
		for (int i = size - 1; i >= index; i--) {
			elementData[i + 1] = elementData[i];
		}
	}

	private void moveForwardFrom(int index) {
		for (int i = index; i < size; i++) {
			elementData[i - 1] = elementData[i];
		}
		elementData[size] = null;
	}

	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOf(elementData, size));
	}

}

class NoElementException extends RuntimeException {

	public NoElementException(String string) {
		super(string);
	}

}
