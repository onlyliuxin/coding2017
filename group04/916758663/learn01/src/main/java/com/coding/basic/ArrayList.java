package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[3];
	
	public void add(Object o){
		add(size,o);
	}
	public void add(int index, Object o){
		if (index > size){
			throw new IndexOutOfBoundsException();
		}
		// 扩容
		if (size == elementData.length || index + 1 > elementData.length) {
			int newLength = index + 1 > size * 2 ? index + 1 :size * 2;
			elementData = Arrays.copyOf(elementData, newLength);
		}
		// 移动元素
		System.arraycopy(elementData,index,elementData,index + 1 ,size-index);
		elementData[index] = o;
		size ++ ;
	}
	
	public Object get(int index){
		checkIndex(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		checkIndex(index);
		Object removed = elementData[index];
		System.arraycopy(elementData,index + 1,elementData,index,size-1 - index);
		size --;
		return removed;
	}

	private void checkIndex(int index) {
		if (index > size-1){
			throw new IndexOutOfBoundsException();
		}
	}

	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator {

		private int currentIndex = 0;

		@Override
		public boolean hasNext() {
			return currentIndex < size();
		}

		@Override
		public Object next() {
			Object o = get(currentIndex);
			currentIndex ++ ;
			return o;
		}
	}
	
}
