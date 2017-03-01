package com.coding.basic; 

import java.util.Arrays;


/**
 * @Description 简单实现ArrayList
 */
public class SimpleArrayList implements SimpleList{
	private int size = 0;
	private Object[] elementData = new Object[10];
	/**
	 * 插入元素o
	 * @param o 待插入元素
	 */
	public void add(Object o){
		//扩容
		if(elementData.length < size + 1){
			grow(size+1);
		}
		elementData[size++] = o; 
	}
	/**
	 * 数组扩容
	 * @param capacity 数组实际长度
	 */
	private void grow(int capacity) {
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);//扩容50%
		if(capacity > newCapacity){
			newCapacity = capacity;
		}
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
	/**
	 * 在指定索引初插入元素
	 * @param index 索引
	 * @param o 待插入元素
	 */
	public void add(int index,Object o){
		rangeCheckForAdd(index);
		if(elementData.length<size+1){
			//扩容
			grow(size+1);
		}
		elementData[index] = o;
		size++;
	}
	/**
	 * 插入位置越界处理
	 * @param index 索引
	 */
	private void rangeCheckForAdd(int index) {
		if(index > size || index <0){
			throw new IndexOutOfBoundsException("数组越界异常");
		}
	}
	/**
	 * 索引越界处理
	 * @param index 索引
	 */
	private void rangeCheck(int index) {
		if(index >= size || index <0)
		throw new IndexOutOfBoundsException("数组越界异常");
	}
	/**
	 * 移除该索引处元素
	 * @param index 索引位置
	 * @return 移除元素
	 */
	public Object remove(int index){
		rangeCheck(index);
		Object oldObject = elementData[index];
		if(size > index +1){
			System.arraycopy(elementData, index +1 , elementData, index, size-index-1);
		}
		elementData[--size] = null;
		return oldObject;
	}
	/**
	 * 返回集合长度
	 * @return 长度
	 */
	public int size(){
		return this.size;
	}
	/**
	 * 返回指定索引元素
	 * @param index 索引
	 * @return Object 元素
	 */
	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}
	private class ArrayListIterator implements SimpleIterator{
		SimpleArrayList l = null;
		private int iteratorIndex = 0;
		
		private ArrayListIterator(SimpleArrayList arrayList){
			this.l = arrayList;
		}
		
		@Override
		public boolean hasNext() {
			return iteratorIndex < size;
		}

		@Override
		public Object next() {
			if (iteratorIndex >= size) {
                throw new RuntimeException("数组越界异常");
            }
            return elementData[iteratorIndex++];
		}
		
	}
}
