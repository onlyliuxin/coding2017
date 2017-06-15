package com.coding.basic.array;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		add(size, o);
	}
	public void add(int index, Object o){
		checkIndex(index);

		elementData[index] = o;

		for (int i = size - 1; i > index; i--) {
			elementData[i + 1] = elementData[i];
		}

		size++;
	}
	
	public Object get(int index){
		checkIndex(index);
		if (index == size)
			throw new IndexOutOfBoundsException("index超过list容量");
		return elementData[index];
	}
	
	public Object remove(int index){
		checkIndex(index);
		if (index == size)
			throw new IndexOutOfBoundsException("index超过list容量");

		Object o = elementData[index];
		if (index != size - 1) {
			for (int i = index; i < size - 1; i++) {
				elementData[i] = elementData[i + 1];
			}
		}
		size--;
		return o;
	}
	
	public int size(){
		return size;
	}

	private class ArrayListIterator implements Iterator {

		private int cursor;

		public ArrayListIterator(int index) {
			this.cursor = index;
		}

		@Override
		public boolean hasNext() {
			return cursor < size - 1 && cursor >= 0 ;
		}

		@Override
		public Object next() {
			if (hasNext())
				return elementData[cursor];
			return null;
		}
	}

	public Iterator iterator(){
		return new ArrayListIterator(0);
	}

	private void checkIndex(int index){
		if(index < 0 || index >= elementData.length)
			throw new IndexOutOfBoundsException("index超过数组最大容量或数目不对");
		if(index > size)
			throw new IndexOutOfBoundsException("index超过list容量");
	}
	
}
