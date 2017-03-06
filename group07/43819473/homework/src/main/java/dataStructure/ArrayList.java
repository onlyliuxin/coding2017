package dataStructure;

/**
 * Created by zj on 2017/2/20.
 */
public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		if (size > elementData.length / 2) {
			elementData = grow(elementData);
		}

		elementData[size] = o;
		size++;
	}

	private Object[] grow(Object[] datas) {
		Object[] elementDataNew = new Object[elementData.length + elementData.length / 4];
		System.arraycopy(datas, 0, elementDataNew, 0, size);
		return elementDataNew;
	}

	public void add(int index, Object o) {

		if (index <0 || index > size - 1) {
			throw new IndexOutOfBoundsException("index out of bound");
		}

		if (size > elementData.length / 2) {
			elementData = grow(elementData);
		}

		for (int i = size - 1; i >= index; i--) {
			elementData[i + 1] = elementData[i];
		}
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		if (index <0 || index > size - 1) {
			throw new IndexOutOfBoundsException("index out of bound");
		}
		return elementData[index];
	}

	public Object remove(int index) {
		if (index <0 || index > size - 1) {
			throw new IndexOutOfBoundsException("index out of bound");
		}

		for (int i = index; i <= size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		elementData[size - 1] = null;
		size--;
		return null;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new ArrayListInterator();
	}

	private class ArrayListInterator implements Iterator {

		private int nowIndex = 0;

		public boolean hasNext() {
			if (nowIndex <= size - 1) {
				return true;
			}
			return false;
		}

		public Object next() {
			return elementData[nowIndex++];
		}
	}
}
