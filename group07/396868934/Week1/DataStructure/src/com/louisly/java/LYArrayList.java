package com.louisly.java;

import com.louisly.java.LYIterator;

public class LYArrayList<T> {
	private Object[] dataArray = new Object[10];
	private int size = 0;
	
	
	public void addObject(T obj) {
		ensureCapacity(size + 1);
		dataArray[size] = obj;
		size++;
	}
	
	public void add(int index, T element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		ensureCapacity(size + 1);
		System.arraycopy(dataArray, index, dataArray, index + 1, size - index);
		dataArray[index] = element;
		size++;
	}
	
	public T set(int index, T element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		dataArray[index] = element;
		return element;
	}
	
	public boolean removeObject(T obj) {
		boolean existObj = false;
		int removeIndex = -1;
		int index = size;
		for (int i = 0; i < index; i++) {
			Object element = dataArray[i];
			boolean remove = false;
			if (element != null && element.equals(obj)) {
				dataArray[i] = null;
				existObj = true;
				remove = true;
				// 以防存在一样的第二个元素
				if (removeIndex == -1) {
					removeIndex = i;
				}
				size--;
			}
			// 将元素往前移动
			if (!remove) {
				dataArray[removeIndex] = element;
				dataArray[i] = null;
				removeIndex++;
			}
		}
		return existObj;
	}
	
	public boolean removeAtIndex(int index) {
		if (index > size) {
			return false;
		}
		dataArray[index] = null;
		
		for (int i = index+1; i < size; i++) {
			dataArray[i-1] = dataArray[i];
			dataArray[i] = null;
		}
		size--;
		return true;
	}
	
	public void clear() {
		for (int i = 0; i < size; i++) {
			dataArray[i] = null;
		}
		size = 0;
	}
	
	public void ensureCapacity(int minCapacity) {
		if (minCapacity < dataArray.length) {
			int newCapacity = Math.max(minCapacity, dataArray.length * 2);
			Object[] newDataArray = new Object[newCapacity];
			System.arraycopy(dataArray, 0, newDataArray, 0, dataArray.length);
			dataArray = newDataArray;
		}
	}
	
	@SuppressWarnings("unchecked")
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return (T) dataArray[index];
	}
	
	public int size() {
		return size;
	}
	
	public LYIterator iterator() {
		return new LYArrayListIterator();
	}
	
	
	private class LYArrayListIterator implements LYIterator {
		private int position;
		
		@Override
		public boolean hasNext() {
			return position < size();
		}
		
		@Override
		public Object next() {
			if (hasNext()) {
				return dataArray[position];
			}
			return null;
		}
		
		public void remove() {
			
		}
	}
}
