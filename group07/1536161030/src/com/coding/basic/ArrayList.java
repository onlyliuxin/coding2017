package com.coding.basic;

public class ArrayList implements List{

	private int size = 0;

	private Object[] elementData;

	public ArrayList(int size) {
		this.elementData = new Object[size];
	}

	public ArrayList() {
		this.elementData = new Object[10];
	}

	public void add(Object o) {
		if(isFull())
			resize();
		elementData[size++] = o;
	}

	public void add(int index, Object o) {
		rangeCheckForAdd(index);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		rangeCheckForAdd(index);
		return elementData[index];
	}

	public Object remove(int index) {
		rangeCheckForAdd(index);
		Object o = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index);
		elementData[--size] = null;
		return o;
	}

	public int size() {
		return elementData.length;
	}

	public com.coding.basic.Iterator iterator() {
		return new Itr();
	}

	private class Itr implements com.coding.basic.Iterator {
		int cursor;

		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@Override
		public Object next() {
			int i = cursor;
			if (i < elementData.length) {
				cursor = i + 1;
				return elementData[i];
			}
			return null;
		}
	}
	
	//檢查下表越界
	public void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) 
			throw new IndexOutOfBoundsException("下标越界");
	}
	
	//数组是否满
	public boolean isFull(){
    	return size == elementData.length;
    }
    
    //扩容
    public void resize(){
    	Object[] newElementData = new Object[elementData.length * 2];
    	System.arraycopy(elementData, 0, newElementData, 0, size);
    	this.elementData = newElementData;
    	newElementData = null;
    }
    
    //
	public boolean isEmpty() {
		return size == 0;
	}
	

}
