package com.coding.basic;

/**
 * @deprecated 用数组实现list
 * @author wang
 *
 */
public class MyArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		// 确保数组大小
		ensureCapacity(size + 1);
		// 数组赋值并使得size+1；
		elementData[size ++] = o;
		size ++;
	}
	
	/**
	 * 确保数组不越界，否则就扩容；
	 * @param minCapacity	list的大小；
	 */
	public void ensureCapacity(int minCapacity) {
		int oldCapacity = elementData.length;
		int newCapacity = (oldCapacity / 3) * 2 + 1;
		if (minCapacity > newCapacity) {
			// 对数组扩容
			Object[] newDate = new Object[newCapacity];
			System.arraycopy(elementData, 0, newDate, 0, oldCapacity);
			elementData = newDate;
		}
	}
	
	public void add(int index, Object o){
		// 对index进行校验：
		rangeCheck(index);
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size ++;
	}
	
	/**
	 * 边界检查：
	 * @param index
	 */
	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("size" + size + ", index:"
					+ index);
		}
	}
	
	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		rangeCheck(index);
		Object oldValue = elementData[index];
		System.arraycopy(elementData, index, elementData, index - 1, size - index);
		return oldValue;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
