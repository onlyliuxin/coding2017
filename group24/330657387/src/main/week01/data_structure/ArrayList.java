package main.week01.data_structure;

import java.util.Arrays;

import main.week01.data_structure.api.Iterator;
import main.week01.data_structure.api.List;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[10];

	private void ensureCapacity(int input) {
		if (input > elementData.length) {
			growCapacity();
		}
	}
	
	private void growCapacity() {
		elementData = Arrays.copyOf(elementData, size * 2);
	}
	
	private void rangeCheck(int index) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public void add(Object o) {
		ensureCapacity(size + 1);
		elementData[size++] = o;
	}

	public void add(int index, Object o) {
		rangeCheck(index);
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size
				- index);
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		rangeCheck(index);
		return elementData[index];
	}

	public Object remove(int index) {
		rangeCheck(index);
		Object dest = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size
				- index - 1);
		elementData[size---1]=null;//��ֹ�ڴ�й©
		return dest;
	}

	public int size() {
		return size;
	}

	public class ArrayListIterator implements Iterator {

		private ArrayList list;

		private int position = 0;

		private ArrayListIterator() {
		}

		private ArrayListIterator(ArrayList list) {
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			return position + 1 <= list.size;
		}

		@Override
		public Object next() {
			return list.get(position++);
		}

	}

	public ArrayListIterator iterator() {
		return new ArrayListIterator(this);
	}

}
