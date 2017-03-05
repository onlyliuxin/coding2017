package com.coding.basic;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o) {
		if(elementData.length == size) {
			Object[] arrTaget = new Object[size * 2];
			System.arraycopy(elementData, 0, arrTaget, 0, size);
			this.elementData = arrTaget;
		}
		
		elementData[size++] = o;
	}
	
	public void add(int index, Object o){
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Index out of bound");
		}
		Object[] arrTarget = new Object[size - index];
		System.arraycopy(elementData, index, arrTarget, 0, size - index);
		elementData[index] = o;
		System.arraycopy(arrTarget, 0, elementData, index + 1, size - index);
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		Object retObj = elementData[index];
		
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Index out of bound");
		}
		else if(index == size) {
			elementData[index] = null; 
		}
		else {
			System.arraycopy(elementData, index + 1, elementData, index, size - index);
		}
		
		size--;
		return retObj;
	}
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator{

		private int cursor = 0;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return this.cursor != size;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return elementData[this.cursor++];
		}
	}
	
}
