package com.coding.basic;

import java.util.Arrays;

/**
 * 2017/2/24
 * @author 236995728
 *
 */
public class ArrayList implements List {
	
	private static int size = 0;
	
	private static Object[] elementData = new Object[100];

	/**
	 * 添加元素
	 */
	@Override
	public void add(Object o){
		if(size >= elementData.length){
			grow(size);
		}
		elementData[size++] = o;
	}
	
	/**
	 * 按索引添加元素
	 */
	@Override
	public void add(int index, Object o){
		if(index < 0){
			throw new IllegalArgumentException("param invalid");
		}
		if(index >= elementData.length){
			grow(index);
		}
		for(int i=index; i<=size; i++){
			elementData[i] = elementData[i+1];
		}
		elementData[index] = o;
		size ++;
	}
	
	/**
	 * 根据索引获取元素
	 */
	@Override
	public Object get(int index){
		if(index<0 || index >size){
			throw new IllegalArgumentException("param invalid");
		}
		return elementData[index];
	}
	
	/**
	 * 根据索引删除元素
	 */
	@Override
	public Object remove(int index){
		if(index<0 || index >size){
			throw new IllegalArgumentException("param invalid");
		}
		Object o = elementData[index];
		for(int i=index;i<size;i++){
			elementData[i] = elementData[i+1];
		}
		size --;
		return o;
	}

	/**
	 * 返回集合元素的个数
	 */
	@Override
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	/**
	 * 扩展底层数组的length,方便动态添加元素
	 * @param capacity
	 */
	private void grow(int capacity){
		int newCapacity = capacity + (capacity >>1); 
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
}
