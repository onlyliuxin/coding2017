package com.coding.basic; 

import java.util.Arrays;

/**
 * @Description 简单实现队列
 */
public class SimpleQueue {
	private Object[] elementData = new Object[10];
	private int size;
	
	/**
	 * 往队尾添加新的元素
	 * @param o 要添加的新元素
	 */
	public void enQueue(Object o){
		if(elementData.length < size +1){
			//扩容
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
		int newCapacity = oldCapacity * 2;
		if(capacity > newCapacity){
			newCapacity = capacity;
		}
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
	/**
	 * 移除并返回队列头部元素
	 * @return 队列头部元素
	 */
	public Object deQueue(){
		Object o = elementData[size-1];
		removeElement(0);
		return o;
	}
	/**
	 * 删除指定索引处元素
	 * @param index 索引
	 */
	private void removeElement(int index) {
		if(index >= 0){
			System.arraycopy(elementData, index+1, elementData, 0, size-index-1);
			elementData[--size] = null;
		}
	}
	/**
	 * 判断队列是否为空
	 * @return Boolean
	 */
	public boolean isEmpty(){
		return size==0;
	}
	/**
	 * 返回队列长度
	 * @return int 队列长度
	 */
	public int size(){
		return this.size;
	}
}
