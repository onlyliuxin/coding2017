public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[5];

	public void add(Object o) {
		if (size >= elementData.length) {
			resize(2 * size);
		}
		elementData[size] = o;
		size++;
	}

	public void add(int index, Object o) {
		if (index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		}
		if (size >= elementData.length) {
			resize(2 * size);
		}
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		}
		return elementData[index];
	}

	public Object remove(int index) {
		Object o = elementData[index];
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		elementData[size - 1] = null;
		size--;
		return o;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return null;
	}

	private void resize(int n) {
		Object[] temp = elementData;
		elementData = new Object[n];
		for (int i = 0; i < temp.length; i++) {
			elementData[i] = temp[i];
		}
	}

}
