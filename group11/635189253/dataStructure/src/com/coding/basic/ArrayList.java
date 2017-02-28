package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public boolean add(Object o){
		add(size(), o);
		size++;
		return true;
	}
	public boolean add(int index, Object o){
		if (index >= 0 && index < elementData.length) {
			for (int i = size(); i > index; i--) {
				elementData[i] = elementData[i-1];
			}
			elementData[index] = o;
		}
		size++;
		return true;
	}
	
	public Object get(int index){
		if (index >= 0 && index < size()) {
			return elementData[index];
		}
//		return null;
		throw  new ArrayIndexOutOfBoundsException();
	}
	
	public Object remove(int index){
		Object removedItem = elementData[index];
		if (index > size()) {
			throw  new ArrayIndexOutOfBoundsException();
		}
		for (int i = index; i < size() - 1; i++) {
			elementData[i] = elementData[i+1];
		}
		size--;
		return removedItem;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator {

		private int current = 0;

		public boolean hasNext() {
			return current < size();
		}
		
		public Object next() {
/*			if (elementData[current+1] != null) {
				return elementData[current+1];
			} else {
				return null;
			}*/
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			return elementData[current++];
		}
	}
	
}
