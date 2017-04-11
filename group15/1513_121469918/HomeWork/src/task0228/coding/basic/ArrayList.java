package task0228.coding.basic;

import java.util.NoSuchElementException;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		int len = size + 1;
		
		if (len > elementData.length) {
			
			Object[] newElemDate = new Object[elementData.length + 1];
			
			System.arraycopy(elementData, 0, newElemDate, 0, elementData.length);
			elementData = newElemDate;
		}
		elementData[size] = o;
		size++;
	}

	public void add(int index, Object o) {
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
		}
		
		if (index == size) {
			add(o);
		} else {
			
			Object[] newElemData = new Object[elementData.length + 1];
			
			System.arraycopy(elementData, 0, newElemData, 0, index);
			newElemData[index] = o;
			
			System.arraycopy(elementData, index, newElemData, index + 1, size - index);

			elementData = newElemData;
			size++;
		}
	}

	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
		}
		return elementData[index];
	}

	public Object remove(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException("index:" + index + "size:" + size);
		}
		Object removeElement = elementData[index];
		
		if(index != (size-1)){
			
			Object[] newElemData = new Object[elementData.length];
			
			System.arraycopy(elementData, 0, newElemData, 0, index);
			
			System.arraycopy(elementData, index+1, newElemData, index, size - index -1);			
		}
		
		size--;
		return removeElement;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new MyIterator();
	}

	private class MyIterator implements Iterator {
		private int cursor = 0;
		

		private MyIterator() {}

		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub			
			if (cursor >= size) {			
				throw new IndexOutOfBoundsException();
			}
			return get(cursor++);
		}

		@Override
		public Object remove() {
			// TODO Auto-generated method stub
			if (cursor <= 0) {
				throw new NoSuchElementException();
			}
			Object val = ArrayList.this.remove(--cursor);
			return val;
		}
	}
}
