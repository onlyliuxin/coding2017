package com.easy.util.myarraylist;

import com.easy.util.myiterator.Iterator;

public class ArrayList{

	private static final Object[] EMPTY_ELEMENTDATA = {};

	private Object[] elementData;

	private int size;
	

	// region 构造函数
	public ArrayList() {
		this.elementData = EMPTY_ELEMENTDATA;
	}

	public ArrayList(int initialCapacity) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal Capacity:" + initialCapacity);
		}
		this.elementData = new Object[initialCapacity];
	}
	// endregion

	// region add方法
	public boolean add(Object o) {
		if (elementData.length <= size) {
			grow(1);
		}
		elementData[size++] = o;
		return true;
	}

	public void add(int index, Object o) {
		rangeCheckForAdd(index);
		grow(1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}
	// endregion

	// region get方法
	public Object get(int index) {
		rangeCheck(index);
		return elementData[index];
	}
	// endregion

	// region remove方法
	public Object remove(int index) {
		rangeCheck(index);

		Object oldValue = elementData[index];
		/*
		 * int numMoved=size-index-1; if(numMoved>0){
		 * System.arraycopy(elementData, index+1, elementData, index, numMoved);
		 * } elementData[--size]=null;
		 */
		fastRemove(index);
		return oldValue;
	}

	public boolean remove(Object o) {
		if (o == null) {
			for (int index = 0; index < size; index++) {
				if (elementData[index] == null) {
					fastRemove(index);
					return true;
				}
			}
		} else {
			for (int index = 0; index < size; index++) {
				if (elementData[index].equals(o)) {
					fastRemove(index);
					return true;
				}
			}
		}
		return false;
	}
	// endregion

	// region size方法
	public int size() {
		return size;
	}
	// endregion

	// region toString方法
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size(); i++) {
			sb.append(elementData[i] + ",");
		}
		/*
		 * for (Object object : elementData) { sb.append(object + ","); }
		 */
		String temp = sb.toString();
		temp = temp.substring(0, temp.length() - 1);
		return "[" + temp + "]";
	}
	// endregion

	// region 私有方法
	private void grow(int minCapacity) {
		Object[] dest = new Object[elementData.length + minCapacity];
		System.arraycopy(elementData, 0, dest, 0, elementData.length);
		elementData = dest;
	}

	private void rangeCheck(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}

	private String outOfBoundsMsg(int index) {
		return "Index:" + index + ",Size:" + size;
	}

	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}

	private void fastRemove(int index) {
		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		}
		elementData[--size] = null;
	}

	// endregion

	// region 下一版本迭代的功能
	private static final int DEFAULT_CAPACITY = 10;

	private void ensureCapacityInternal(int minCapacity) {
		if (elementData == EMPTY_ELEMENTDATA) {
			minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
		}
		ensureCapacityInternal(minCapacity);
	}

	private void ensureExplicitCapacity(int minCapacity) {
		if (minCapacity - elementData.length > 0) {
			grow(minCapacity);
		}
	}
	//endregion
	
	int currentIndex ;
	public Iterator iterator(){
		currentIndex=-1;
		return new Iterator() {
			
			@Override
			public Object next() {
				return elementData[++currentIndex];
			}
			
			@Override
			public boolean hasNext() {
				return currentIndex<size-1;
			}
		};
	}
	
}
